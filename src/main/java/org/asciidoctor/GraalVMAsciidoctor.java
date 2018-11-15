package org.asciidoctor;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GraalVMAsciidoctor implements Asciidoctor {

  private static GraalVMAsciidoctor instance = null;
  private final Value asciidoctor;

  private GraalVMAsciidoctor() throws IOException, URISyntaxException {
    Context context = Context.newBuilder("js").allowIO(true).build();
    context.getPolyglotBindings().putMember("IncludeResolver", new GraalVMIncludeResolver());
    context.eval("js", "var IncludeResolver = Polyglot.import('IncludeResolver');");
    evalJavaScriptResource(context, "asciidoctor.js");
    asciidoctor = context.eval("js", "Asciidoctor()"); // init
  }

  private static Value evalJavaScriptResource(Context context, String resourceName) throws URISyntaxException, IOException {
    List<String> lines = Files.readAllLines(Paths.get(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(resourceName)).toURI()), StandardCharsets.UTF_8);
    String content = String.join("\n", lines);
    return context.eval("js", content);
  }

  public static GraalVMAsciidoctor create() throws IOException, URISyntaxException {
    if (instance == null) {
      instance = new GraalVMAsciidoctor();
    }
    return instance;
  }

  @Override
  public String convert(String content, Map<String, Object> options) {
    Value result = asciidoctor.getMember("convert").execute(content, options);
    return result.asString();
  }

  @Override
  public String asciidoctorVersion() {
    return asciidoctor.getMember("getCoreVersion").execute().asString();
  }
}
