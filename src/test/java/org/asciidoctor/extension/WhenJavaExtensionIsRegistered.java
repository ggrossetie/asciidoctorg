package org.asciidoctor.extension;

import org.asciidoctor.Asciidoctor;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import static org.asciidoctor.OptionsBuilder.options;
import static org.assertj.core.api.Assertions.assertThat;

public class WhenJavaExtensionIsRegistered {

  private Asciidoctor asciidoctor = Asciidoctor.Factory.create();

  @Test
  public void a_block_processor_should_be_executed_when_registered_block_is_found_in_document() throws IOException, URISyntaxException {
    JavaExtensionRegistry javaExtensionRegistry = asciidoctor.javaExtensionRegistry();
    javaExtensionRegistry.block("yell", YellBlock.class);
    String content = asciidoctor.convert(
      readResource("sample-with-yell-block.ad"),
      options().get());

    org.jsoup.nodes.Document doc = Jsoup.parse(content, "UTF-8");
    Elements elements = doc.getElementsByClass("paragraph");
    assertThat(elements.size()).isEqualTo(1);
    assertThat(elements.get(0).text()).isEqualTo("THE TIME IS NOW. GET A MOVE ON.");
  }

  private String readResource(String resourceName) throws URISyntaxException, IOException {
    return new String(Files.readAllBytes(Paths.get(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(resourceName)).toURI())));
  }
}
