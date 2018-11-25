package org.asciidoctor.extension;

public interface JavaExtensionRegistry {
  JavaExtensionRegistry block(String blockName, Class<? extends BlockProcessor> blockProcessor);
}
