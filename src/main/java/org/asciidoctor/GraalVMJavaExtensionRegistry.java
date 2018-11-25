package org.asciidoctor;

import org.asciidoctor.ast.Block;
import org.asciidoctor.ast.StructuralNode;
import org.asciidoctor.ast.StructuralNodeImpl;
import org.asciidoctor.extension.BlockProcessor;
import org.asciidoctor.extension.GraalVMReader;
import org.asciidoctor.extension.JavaExtensionRegistry;
import org.asciidoctor.extension.Reader;
import org.graalvm.polyglot.Value;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class GraalVMJavaExtensionRegistry implements JavaExtensionRegistry {

  private final Value asciidoctor;

  public GraalVMJavaExtensionRegistry(Value asciidoctor) {
    this.asciidoctor = asciidoctor;
  }

  @Override
  public JavaExtensionRegistry block(String blockName, Class<? extends BlockProcessor> blockProcessor) {
    Map<String, BiFunction> functions = new HashMap<>();
    functions.put("process", (BiFunction<Object, Object, Object>) (instance, args) -> {
      Object document = ((Map) args).get("0");
      Object reader = ((Map) args).get("1");
      Object attributes = ((Map) args).get("2");
      try {
        // FIXME: Find the constructor
        BlockProcessor blockProcessorInstance = blockProcessor.newInstance();
        Map<String, Object> config = new HashMap<>();
        config.put("document_instance_$$id", ((Map) document).get("$$id"));
        config.put("processor_instance_$$id", ((Map) instance).get("$$id"));
        blockProcessorInstance.updateConfig(config);
        Block result = (Block) blockProcessorInstance.process(createStructuralNode(document), createReader(reader), createAttributes(attributes));
        return result.getId();
      } catch (IllegalAccessException | InstantiationException e) {
        throw new RuntimeException("Unable to instantiate the blockProcessor");
      }
    });
    Value block = asciidoctor.getMember("Extensions").getMember("newJavaBlockProcessor").execute(blockName, functions);
    asciidoctor.getMember("Extensions").getMember("registerBlock").execute(blockName, block);
    return this;
  }


  private Reader createReader(Object reader) {
    return new GraalVMReader((Map) reader);
  }

  private StructuralNode createStructuralNode(Object document) {
    return new StructuralNodeImpl((Map) document);
  }

  private Map<String, Object> createAttributes(Object attributes) {
    return (Map) attributes;
  }
}
