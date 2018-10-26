package org.asciidoctor;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GraalVMIncludeResolver {
  public String read(String path) throws IOException, URISyntaxException {
    Path filePath = Paths.get(path);
    List<String> lines;
    File file = filePath.toFile();
    if (file.exists()) {
      lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
    } else {
      Path fileName = filePath.getFileName();
      URL url = this.getClass().getClassLoader().getResource(fileName.toString());
      if (url != null) {
        lines = Files.readAllLines(Paths.get(url.toURI()), StandardCharsets.UTF_8);
      } else {
        lines = new ArrayList<>();
      }
    }
    return String.join("\n", lines);
  }

  public String pwd() {
    return Paths.get("").toAbsolutePath().toString();
  }
}
