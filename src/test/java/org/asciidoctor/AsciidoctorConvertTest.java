package org.asciidoctor;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class AsciidoctorConvertTest {

  @Test
  public void should_convert_an_asciidoc_formatted_string_into_html5() {
    Asciidoctor asciidoctor = Asciidoctor.Factory.create();
    String result = asciidoctor.convert("Hello from *GraalVM*", new HashMap<>());
    assertThat(result).isEqualTo("<div class=\"paragraph\">\n" +
      "<p>Hello from <strong>GraalVM</strong></p>\n" +
      "</div>");
  }
}
