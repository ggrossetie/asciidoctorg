package org.asciidoctor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GraalVMIncludeResolver {
  public String read(String path) throws IOException, URISyntaxException {
    URL url = this.getClass().getClassLoader().getResource(path);
    if (url != null) {
      List<String> lines = Files.readAllLines(Paths.get(url.toURI()), StandardCharsets.UTF_8);
      return String.join("\n", lines);
    } else {
      List<String> lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
      return String.join("\n", lines);
    }
  }

  public String pwd() {
    return Paths.get("").toAbsolutePath().toString();
  }
}
