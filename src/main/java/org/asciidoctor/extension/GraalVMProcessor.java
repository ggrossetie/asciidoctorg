package org.asciidoctor.extension;

import org.asciidoctor.GraalVMAsciidoctor;
import org.asciidoctor.ast.Block;
import org.asciidoctor.ast.Cell;
import org.asciidoctor.ast.Column;
import org.asciidoctor.ast.ContentNode;
import org.asciidoctor.ast.Cursor;
import org.asciidoctor.ast.DescriptionList;
import org.asciidoctor.ast.Document;
import org.asciidoctor.ast.ListItem;
import org.asciidoctor.ast.PhraseNode;
import org.asciidoctor.ast.Row;
import org.asciidoctor.ast.Section;
import org.asciidoctor.ast.StructuralNode;
import org.asciidoctor.ast.Table;
import org.graalvm.polyglot.Value;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GraalVMProcessor implements Processor {

  private final Value asciidoctor;
  private Map<String, Object> config;

  public GraalVMProcessor() throws IOException, URISyntaxException {
    this(GraalVMAsciidoctor.create().getAsciidoctor());
  }

  private GraalVMProcessor(Value asciidoctor) {
    this.asciidoctor = asciidoctor;
    this.config = new HashMap<>();
  }

  @Override
  public Table createTable(StructuralNode structuralNode) {
    return null;
  }

  @Override
  public Table createTable(StructuralNode structuralNode, Map<String, Object> map) {
    return null;
  }

  @Override
  public Row createTableRow(Table table) {
    return null;
  }

  @Override
  public Column createTableColumn(Table table, int i) {
    return null;
  }

  @Override
  public Column createTableColumn(Table table, int i, Map<String, Object> map) {
    return null;
  }

  @Override
  public Cell createTableCell(Column column, String s) {
    return null;
  }

  @Override
  public Cell createTableCell(Column column, Document document) {
    return null;
  }

  @Override
  public Cell createTableCell(Column column, Document document, Map<String, Object> map) {
    return null;
  }

  @Override
  public Cell createTableCell(Column column, String s, Map<String, Object> map) {
    return null;
  }

  @Override
  public Block createBlock(StructuralNode structuralNode, String s, String s1) {
    return null;
  }

  @Override
  public Block createBlock(StructuralNode structuralNode, String s, String s1, Map<String, Object> map) {
    return null;
  }

  @Override
  public Block createBlock(StructuralNode structuralNode, String s, String s1, Map<String, Object> map, Map<Object, Object> map1) {
    return null;
  }

  @Override
  public Block createBlock(StructuralNode structuralNode, String s, List<String> list) {
    return null;
  }

  @Override
  public Block createBlock(StructuralNode structuralNode, String s, List<String> list, Map<String, Object> map) {
    return null;
  }

  @Override
  public Block createBlock(StructuralNode structuralNode, String s, List<String> list, Map<String, Object> map, Map<Object, Object> map1) {
    return null;
  }

  @Override
  public Section createSection(StructuralNode structuralNode) {
    return null;
  }

  @Override
  public Section createSection(StructuralNode structuralNode, Map<Object, Object> map) {
    return null;
  }

  @Override
  public Section createSection(StructuralNode structuralNode, boolean b, Map<Object, Object> map) {
    return null;
  }

  @Override
  public Section createSection(StructuralNode structuralNode, int i, boolean b, Map<Object, Object> map) {
    return null;
  }

  @Override
  public PhraseNode createPhraseNode(ContentNode contentNode, String s, List<String> list) {
    return null;
  }

  @Override
  public PhraseNode createPhraseNode(ContentNode contentNode, String s, List<String> list, Map<String, Object> map) {
    return null;
  }

  @Override
  public PhraseNode createPhraseNode(ContentNode contentNode, String s, List<String> list, Map<String, Object> map, Map<Object, Object> map1) {
    return null;
  }

  @Override
  public PhraseNode createPhraseNode(ContentNode contentNode, String s, String s1) {
    return null;
  }

  @Override
  public PhraseNode createPhraseNode(ContentNode contentNode, String s, String s1, Map<String, Object> map) {
    return null;
  }

  @Override
  public PhraseNode createPhraseNode(ContentNode contentNode, String s, String s1, Map<String, Object> map, Map<String, Object> map1) {
    return null;
  }

  @Override
  public Block createBlock(StructuralNode structuralNode, String context, Map<Object, Object> options) {
    // parent, context, source, attrs, opts
    Object processorInstanceId = config.get("processor_instance_$$id");
    Object documentInstanceId = config.get("document_instance_$$id");
    Object attributes = options.get("attributes");
    List<String> source = (List<String>) options.get("source");
    String content = source.stream().collect(Collectors.joining("\n"));
    Value execute = asciidoctor.getMember("Extensions")
      .getMember("Processor")
      .getMember("createJavaBlock")
      .execute(processorInstanceId, documentInstanceId, context, content, attributes, options);
    return new Block() {
      @Override
      public List<String> lines() {
        List<String> lines = new ArrayList<>();
        lines.add(execute.getMember("lines").getArrayElement(0).asString());
        return lines;
      }

      @Override
      public List<String> getLines() {
        List<String> lines = new ArrayList<>();
        lines.add(execute.getMember("lines").getArrayElement(0).asString());
        return lines;
      }

      @Override
      public void setLines(List<String> list) {
      }

      @Override
      public String source() {
        return null;
      }

      @Override
      public String getSource() {
        return null;
      }

      @Override
      public void setSource(String s) {
      }

      @Override
      public String title() {
        return null;
      }

      @Override
      public String getTitle() {
        return null;
      }

      @Override
      public void setTitle(String s) {

      }

      @Override
      public String getCaption() {
        return null;
      }

      @Override
      public void setCaption(String s) {

      }

      @Override
      public String style() {
        return null;
      }

      @Override
      public String getStyle() {
        return null;
      }

      @Override
      public void setStyle(String s) {

      }

      @Override
      public List<StructuralNode> blocks() {
        return null;
      }

      @Override
      public List<StructuralNode> getBlocks() {
        return null;
      }

      @Override
      public void append(StructuralNode structuralNode) {

      }

      @Override
      public Object content() {
        return null;
      }

      @Override
      public Object getContent() {
        return null;
      }

      @Override
      public String convert() {
        return null;
      }

      @Override
      public List<StructuralNode> findBy(Map<Object, Object> map) {
        return null;
      }

      @Override
      public int getLevel() {
        return 0;
      }

      @Override
      public String getContentModel() {
        return null;
      }

      @Override
      public Cursor getSourceLocation() {
        return null;
      }

      @Override
      public List<String> getSubstitutions() {
        return null;
      }

      @Override
      public boolean isSubstitutionEnabled(String s) {
        return false;
      }

      @Override
      public void removeSubstitution(String s) {

      }

      @Override
      public void addSubstitution(String s) {

      }

      @Override
      public void prependSubstitution(String s) {

      }

      @Override
      public void setSubstitutions(String... strings) {

      }

      @Override
      public String id() {
        return null;
      }

      @Override
      public String getId() {
        return String.valueOf(execute.getMember("$$id").asInt());
      }

      @Override
      public void setId(String s) {
      }

      @Override
      public String getNodeName() {
        return null;
      }

      @Override
      public ContentNode parent() {
        return null;
      }

      @Override
      public ContentNode getParent() {
        return null;
      }

      @Override
      public String context() {
        return null;
      }

      @Override
      public String getContext() {
        return null;
      }

      @Override
      public Document document() {
        return null;
      }

      @Override
      public Document getDocument() {
        return null;
      }

      @Override
      public boolean isInline() {
        return false;
      }

      @Override
      public boolean isBlock() {
        return false;
      }

      @Override
      public Map<String, Object> getAttributes() {
        return null;
      }

      @Override
      public Object getAttr(Object o, Object o1, boolean b) {
        return null;
      }

      @Override
      public Object getAttr(Object o, Object o1) {
        return null;
      }

      @Override
      public Object getAttr(Object o) {
        return null;
      }

      @Override
      public Object getAttribute(Object o, Object o1, boolean b) {
        return null;
      }

      @Override
      public Object getAttribute(Object o, Object o1) {
        return null;
      }

      @Override
      public Object getAttribute(Object o) {
        return null;
      }

      @Override
      public boolean hasAttr(Object o) {
        return false;
      }

      @Override
      public boolean hasAttr(Object o, boolean b) {
        return false;
      }

      @Override
      public boolean hasAttribute(Object o) {
        return false;
      }

      @Override
      public boolean hasAttribute(Object o, boolean b) {
        return false;
      }

      @Override
      public boolean isAttr(Object o, Object o1) {
        return false;
      }

      @Override
      public boolean isAttr(Object o, Object o1, boolean b) {
        return false;
      }

      @Override
      public boolean isAttribute(Object o, Object o1) {
        return false;
      }

      @Override
      public boolean isAttribute(Object o, Object o1, boolean b) {
        return false;
      }

      @Override
      public boolean setAttr(Object o, Object o1, boolean b) {
        return false;
      }

      @Override
      public boolean setAttribute(Object o, Object o1, boolean b) {
        return false;
      }

      @Override
      public boolean isOption(Object o) {
        return false;
      }

      @Override
      public boolean isRole() {
        return false;
      }

      @Override
      public boolean hasRole(String s) {
        return false;
      }

      @Override
      public String getRole() {
        return null;
      }

      @Override
      public String role() {
        return null;
      }

      @Override
      public List<String> getRoles() {
        return null;
      }

      @Override
      public void addRole(String s) {

      }

      @Override
      public void removeRole(String s) {

      }

      @Override
      public boolean isReftext() {
        return false;
      }

      @Override
      public String getReftext() {
        return null;
      }

      @Override
      public String iconUri(String s) {
        return null;
      }

      @Override
      public String mediaUri(String s) {
        return null;
      }

      @Override
      public String imageUri(String s) {
        return null;
      }

      @Override
      public String imageUri(String s, String s1) {
        return null;
      }

      @Override
      public String readAsset(String s, Map<Object, Object> map) {
        return null;
      }

      @Override
      public String normalizeWebPath(String s, String s1, boolean b) {
        return null;
      }
    };
  }

  @Override
  public Section createSection(StructuralNode structuralNode, Integer integer, boolean b, Map<Object, Object> map) {
    return null;
  }

  @Override
  public Document createDocument(Document document) {
    return null;
  }

  @Override
  public ListItem createListItem(org.asciidoctor.ast.List list, String s) {
    return null;
  }

  @Override
  public ListItem createListItem(DescriptionList descriptionList, String s) {
    return null;
  }

  @Override
  public void parseContent(StructuralNode structuralNode, List<String> list) {

  }

  @Override
  public Map<String, Object> getConfig() {
    return config;
  }

  @Override
  public void setConfig(Map<String, Object> config) {
    this.config = config;
  }

  @Override
  public void updateConfig(Map<String, Object> config) {
    this.config.putAll(config);
  }

  @Override
  public void setConfigFinalized() {

  }
}
