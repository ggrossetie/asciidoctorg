package org.asciidoctor;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class AsciidoctorConvertTest {

  private Asciidoctor asciidoctor = Asciidoctor.Factory.create();

  @Test
  public void should_convert_an_asciidoc_formatted_string_into_html5_with_default_options() {
    String result = asciidoctor.convert("Hello from *GraalVM*", new HashMap<>());
    assertThat(result).isEqualTo("<div class=\"paragraph\">\n" +
      "<p>Hello from <strong>GraalVM</strong></p>\n" +
      "</div>");
  }

  @Test
  public void should_convert_an_asciidoc_formatted_string_into_html5_with_title_attribute() {
    HashMap<String, Object> options = new HashMap<>();
    HashMap<String, Object> attributes = new HashMap<>();
    attributes.put("showtitle", true);
    options.put("attributes", attributes);
    String result = asciidoctor.convert("= Title\n\nHello from *GraalVM*", options);
    assertThat(result).isEqualTo("<h1>Title</h1>\n" +
      "<div class=\"paragraph\">\n" +
      "<p>Hello from <strong>GraalVM</strong></p>\n" +
      "</div>");
  }

  @Test
  public void should_convert_an_asciidoc_formatted_string_into_html5_without_title_attribute() {
    HashMap<String, Object> options = new HashMap<>();
    HashMap<String, Object> attributes = new HashMap<>();
    attributes.put("showtitle", false);
    options.put("attributes", attributes);
    String result = asciidoctor.convert("= Title\n\nHello from *GraalVM*", options);
    assertThat(result).isEqualTo("<div class=\"paragraph\">\n" +
      "<p>Hello from <strong>GraalVM</strong></p>\n" +
      "</div>");
  }

  @Test
  public void should_return_an_empty_string_when_converting_a_document_with_inline_doctype() {
    HashMap<String, Object> options = new HashMap<>();
    options.put("doctype", "inline");
    String result = asciidoctor.convert("= Document Title\n\n== Introduction\n\nA simple paragraph.", options);
    assertThat(result).isEqualTo("");
  }
}
