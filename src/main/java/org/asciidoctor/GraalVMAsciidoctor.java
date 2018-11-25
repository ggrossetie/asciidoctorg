package org.asciidoctor;

import org.asciidoctor.extension.JavaExtensionRegistry;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;

public class GraalVMAsciidoctor implements Asciidoctor {

  private static GraalVMAsciidoctor instance = null;
  private final Value asciidoctor;

  private GraalVMAsciidoctor() throws IOException, URISyntaxException {
    Context context = Context.newBuilder("js").allowIO(true).build();
    context.getPolyglotBindings().putMember("IncludeResolver", new GraalVMIncludeResolver());
    context.eval("js", "var IncludeResolver = Polyglot.import('IncludeResolver');");
    context.eval("js", "var ExtensionProcess = Polyglot.import('ExtensionProcess');");
    evalJavaScriptResource(context, "asciidoctor.js");
    asciidoctor = context.eval("js", "Asciidoctor()"); // init
    evalJavaScriptResource(context, "asciidoctor-graalvm.js");
  }

  private static Value evalJavaScriptResource(Context context, String resourceName) throws URISyntaxException, IOException {
    String content = new String(Files.readAllBytes(Paths.get(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(resourceName)).toURI())));
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
  public JavaExtensionRegistry javaExtensionRegistry() {
    return new GraalVMJavaExtensionRegistry(asciidoctor);
  }

  @Override
  public String asciidoctorVersion() {
    return asciidoctor.getMember("getCoreVersion").execute().asString();
  }

  public Value getAsciidoctor() {
    return asciidoctor;
  }
}
