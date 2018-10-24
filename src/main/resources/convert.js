/**
 * Convert a Java Map to a JSON.
 * @private
 */
var fromHashMap = function(object) {
  if (typeof object.get === 'function') {
    var result = {};
    for (var key in object) {
      var value = object.get(key);
      result[key] = value;
    }
    return result;
  }
  return object;
};

/**
 * Convert a JSON to an (Opal) Hash.
 * @private
 */
var toHash = function (object) {
  return object && !('$$smap' in object) ? Opal.hash(object) : object;
};

/**
 * @private
 */
var prepareOptions = function (options) {
  options = fromHashMap(options);
  var attributes = options['attributes'];
  if (attributes) {
    options['attributes'] = fromHashMap(attributes);
  }
  if (options = toHash(options)) {
    var attrs = options['$[]']('attributes');
    if (attrs && typeof attrs === 'object' && attrs.constructor.name === 'Object') {
      options = options.$dup();
      options['$[]=']('attributes', toHash(attrs));
    }
  }
  return options;
};

function (input, options) {
  if (typeof input === 'object' && input.constructor.name === 'Buffer') {
    input = input.toString('utf8');
  }
  var result = Opal.Asciidoctor.$convert(input, prepareOptions(options));
  return result === Opal.nil ? '' : result;
}
