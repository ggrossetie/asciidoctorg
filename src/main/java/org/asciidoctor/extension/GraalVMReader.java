package org.asciidoctor.extension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GraalVMReader implements Reader {

  private final Map values;

  public GraalVMReader(Map values) {
    this.values = values;
  }

  @Override
  public int getLineno() {
    return 0;
  }

  @Override
  public int getLineNumber() {
    return 0;
  }

  @Override
  public String getFile() {
    return null;
  }

  @Override
  public String getDir() {
    return null;
  }

  @Override
  public boolean hasMoreLines() {
    return false;
  }

  @Override
  public boolean isNextLineEmpty() {
    return false;
  }

  @Override
  public String read() {
    return null;
  }

  @Override
  public List<String> readLines() {
    List<String> result = new ArrayList<>();
    Map lines = (Map) values.get("lines");
    if (null != lines) {
      Set<Map.Entry> entries = (Set<Map.Entry>)lines.entrySet();
      for (Map.Entry entry : entries) {
        result.add((String) entry.getValue());
      }
    }
    return result;
  }

  @Override
  public String readLine() {
    return null;
  }

  @Override
  public List<String> lines() {
    return null;
  }

  @Override
  public void restoreLine(String s) {

  }

  @Override
  public void restoreLines(List<String> list) {

  }

  @Override
  public String peekLine() {
    return null;
  }

  @Override
  public List<String> peekLines(int i) {
    return null;
  }

  @Override
  public boolean advance() {
    return false;
  }

  @Override
  public void terminate() {

  }
}
