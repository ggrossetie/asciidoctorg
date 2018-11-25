// GraalVM

var fromHash = function (hash) {
  var object = {};
  var data = hash.$$smap;
  for (var key in data) {
    object[key] = data[key];
  }
  return object;
};

var fromHashMap = function(object) {
  if (object && typeof object.get === 'function') {
    var result = {};
    for (var key in object) {
      var value = object.get(key);
      result[key] = value;
    }
    return result;
  }
  return object;
};

var Extensions = Opal.const_get_qualified(Opal.Asciidoctor, 'Extensions');

// Alias
Opal.Asciidoctor.Extensions = Extensions;

function initializeJavaClass (superClass, className, functions, defaultFunctions, argProxyFunctions) {
  var scope = Opal.klass(Opal.Object, superClass, className, function () {});
  var postConstructFunction;
  var initializeFunction;
  var defaultFunctionsOverridden = {};
  for (var functionName in functions) {
    if (functions.hasOwnProperty(functionName)) {
      (function (functionName) {
        var userFunction = functions[functionName];
        if (functionName === 'postConstruct') {
          postConstructFunction = userFunction;
        } else if (functionName === 'initialize') {
          initializeFunction = userFunction;
        } else {
          if (defaultFunctions && defaultFunctions.hasOwnProperty(functionName)) {
            defaultFunctionsOverridden[functionName] = true;
          }
          Opal.def(scope, '$' + functionName, function () {
            var args;
            if (argProxyFunctions && argProxyFunctions.hasOwnProperty(functionName)) {
              args = argProxyFunctions[functionName](arguments);
            } else {
              args = arguments;
            }
            var argumentsList =  Array.from(arguments);
            for (var i = 0; i < argumentsList.length; i++) {
              // convert all (Opal) Hash arguments to JSON.
              if (typeof argumentsList[i] === 'object' && argumentsList[i].$$smap) {
                argumentsList[i] = fromHash(argumentsList[i]);
              }
            }
            Opal.Asciidoctor.Extensions.JavaDocumentRegistry[argumentsList[0].$$id] = argumentsList[0];
            const id = userFunction.apply(this, argumentsList);
            return Opal.Asciidoctor.Extensions.JavaBlockRegistry[id];
          });
        }
      }(functionName));
    }
  }
  var initialize;
  if (typeof initializeFunction === 'function') {
    initialize = function () {
      initializeFunction.apply(this, arguments);
      if (typeof postConstructFunction === 'function') {
        postConstructFunction.bind(this)();
      }
    };
  } else {
    initialize = function () {
      Opal.send(this, Opal.find_super_dispatcher(this, 'initialize', initialize));
      if (typeof postConstructFunction === 'function') {
        postConstructFunction.bind(this)();
      }
    };
  }
  Opal.def(scope, '$initialize', initialize);
  Opal.def(scope, 'super', function (func) {
    if (typeof func === 'function') {
      Opal.send(this, Opal.find_super_dispatcher(this, func.name, func));
    } else {
      // Bind the initialize function to super();
      var argumentsList =  Array.from(arguments);
      for (var i = 0; i < argumentsList.length; i++) {
        // convert all (Opal) Hash arguments to JSON.
        if (typeof argumentsList[i] === 'object') {
          argumentsList[i] = toHash(argumentsList[i]);
        }
      }
      Opal.send(this, Opal.find_super_dispatcher(this, 'initialize', initialize), argumentsList);
    }
  });
  if (defaultFunctions) {
    for (var defaultFunctionName in defaultFunctions) {
      if (defaultFunctions.hasOwnProperty(defaultFunctionName) && !defaultFunctionsOverridden.hasOwnProperty(defaultFunctionName)) {
        (function (defaultFunctionName) {
          var defaultFunction = defaultFunctions[defaultFunctionName];
          Opal.def(scope, '$' + defaultFunctionName, function () {
            return defaultFunction.apply(this, arguments);
          });
        }(defaultFunctionName));
      }
    }
  }
  return scope;
}
function initializeJavaProcessorClass (superclassName, className, functions) {
  var superClass = Opal.const_get_qualified(Extensions, superclassName);
  return initializeJavaClass(superClass, className, functions, {
    'handles?': function () {
      return true;
    }
  });
}

var JavaProcessorRegistry = {};
var JavaDocumentRegistry = {};
var JavaBlockRegistry = {};

// Java Registry
Opal.Asciidoctor.Extensions.JavaProcessorRegistry = JavaProcessorRegistry;
Opal.Asciidoctor.Extensions.JavaDocumentRegistry = JavaDocumentRegistry;
Opal.Asciidoctor.Extensions.JavaBlockRegistry = JavaBlockRegistry;

Extensions.createJavaBlockProcessor = function (name, functions) {
  return initializeJavaProcessorClass('BlockProcessor', name, functions);
};
/**
 * Create and instantiate a postprocessor
 * @description this API is experimental and subject to change
 * @memberof Extensions
 */
Extensions.newJavaBlockProcessor = function (name, functions) {
  let processor = this.createJavaBlockProcessor(name, fromHashMap(functions)).$new();
  JavaProcessorRegistry[processor.$$id] = processor;
  return processor;
};

Extensions.registerBlock = function (name, block) {
  this.register(function () {
    this.block(name, block)
  })
};

var Processor = Opal.Asciidoctor.Extensions.Processor['$$class'];

// Alias
Opal.Asciidoctor.Extensions.Processor = Processor;

/**
 * Convert a JSON to an (Opal) Hash.
 * @private
 */
var toHash = function (object) {
  return object && !('$$smap' in object) ? Opal.hash(object) : object;
};

Processor.prototype.createJavaBlock = function (processorId, documentId, context, source, attrs, opts) {
  let processor = JavaProcessorRegistry[processorId];
  let document = JavaDocumentRegistry[documentId];
  let attributes = toHash(fromHashMap(attrs));
  console.log(attributes)
  let options = toHash(fromHashMap(opts));
  let block = processor.$create_block(document, context, source, attributes, Opal.hash({}));
  Opal.Asciidoctor.Extensions.JavaBlockRegistry[block.$$id] = block;
  return block;
};
