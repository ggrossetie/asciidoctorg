package org.asciidoctor;

import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class WhenIncludeDirectiveIsUsed {

  private Asciidoctor asciidoctor = Asciidoctor.Factory.create();

  @Test
  public void should_resolve_include_target_in_classpath() {
    String result = asciidoctor.convert("include::include.adoc[]", OptionsBuilder.options().safe(SafeMode.SAFE));
    assertThat(result).isEqualTo("<div class=\"paragraph\">\n" +
      "<p>include content</p>\n" +
      "</div>");
  }

  @Test
  public void should_resolve_include_target_in_filesystem() {
    String pwd = Paths.get("").toAbsolutePath().toString();
    String result = asciidoctor.convert("include::" + pwd + "/test/resources/include.adoc[]", OptionsBuilder.options().safe(SafeMode.SAFE));
    assertThat(result).isEqualTo("<div class=\"paragraph\">\n" +
      "<p>include content</p>\n" +
      "</div>");
  }
}
