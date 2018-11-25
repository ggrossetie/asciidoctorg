package org.asciidoctor;

import org.asciidoctor.extension.JavaExtensionRegistry;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public interface Asciidoctor {
  /**
   * Parse the AsciiDoc source input into an Document and
   * render it to the specified backend format.
   * <p>
   * Accepts input as String object.
   *
   * @param content the AsciiDoc source as String.
   * @param options a Hash of options to control processing (default: {}).
   * @return the rendered output String is returned
   */
  String convert(String content, Map<String, Object> options);

  /**
   * Parse the AsciiDoc source input into an Document and
   * render it to the specified backend format.
   * <p>
   * Accepts input as String object.
   *
   * @param content the AsciiDoc source as String.
   * @param options a Hash of options to control processing (default: {}).
   * @return the rendered output String is returned
   */
  default String convert(String content, Options options) {
    return convert(content, options.map());
  }

  /**
   * Parse the AsciiDoc source input into an Document and
   * render it to the specified backend format.
   * <p>
   * Accepts input as String object.
   *
   * @param content the AsciiDoc source as String.
   * @param options a Hash of options to control processing (default: {}).
   * @return the rendered output String is returned
   */
  default String convert(String content, OptionsBuilder options) {
    return convert(content, options.asMap());
  }

  /**
   * Creates an extension registry ready to be used for registering all processors
   *
   * @return Extension Registry object.
   */
  JavaExtensionRegistry javaExtensionRegistry();

  /**
   * Method that gets the asciidoctor version which is being used..
   *
   * @return Version number.
   */
  String asciidoctorVersion();

  final class Factory {

    private Factory() {
    }

    /**
     * Creates a new instance of Asciidoctor.
     *
     * @return Asciidoctor instance which uses the GraalVM polyglot API to wrap Asciidoctor.js
     */
    public static Asciidoctor create() {
      try {
        return GraalVMAsciidoctor.create();
      } catch (IOException | URISyntaxException e) {
        throw new RuntimeException("Unable to instantiate Asciidoctor.js");
      }
    }
  }
}
