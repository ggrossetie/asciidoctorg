package org.asciidoctor.extension;

import org.asciidoctor.ast.ContentModel;
import org.asciidoctor.ast.StructuralNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YellBlock extends BlockProcessor {

  private static Map<String, Object> configs = new HashMap<String, Object>() {{
    put(Contexts.KEY, Arrays.asList(Contexts.PARAGRAPH));
    put(ContentModel.KEY, ContentModel.SIMPLE);
  }};

  public YellBlock() {
    this("yell", configs);
  }

  public YellBlock(String name) {
    super(name, configs);
  }

  public YellBlock(String name, Map<String, Object> config) {
    super(name, configs);
  }

  @Override
  public Object process(StructuralNode parent, Reader reader, Map<String, Object> attributes) {
    List<String> lines = reader.readLines();
    String upperLines = null;
    for (String line : lines) {
      if (upperLines == null) {
        upperLines = line.toUpperCase();
      } else {
        upperLines = upperLines + "\n" + line.toUpperCase();
      }
    }

    return createBlock(parent, "paragraph", Arrays.asList(upperLines), attributes, new HashMap<>());
  }

}
