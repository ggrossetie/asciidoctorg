package org.asciidoctor.ast;

import java.util.List;
import java.util.Map;

public class StructuralNodeImpl implements StructuralNode {

  private final Map values;

  public StructuralNodeImpl(Map values) {
    this.values = values;
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
    return null;
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
}
