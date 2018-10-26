package org.asciidoctor;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WhenUserRequiresTheAsciidoctorRuntimeVersion {

  private Asciidoctor asciidoctor = Asciidoctor.Factory.create();

  @Test
  public void current_version_should_be_retrieved() {
    String asciidoctorVersion = asciidoctor.asciidoctorVersion();
    assertThat(asciidoctorVersion).isEqualTo("1.5.8.dev");
  }
}
