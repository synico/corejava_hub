package com.ds.graph;

public class Vertex {

  private Integer topNum = -1;

  private Integer indegree = -1;

  private Integer value;

  public Vertex(Integer value) {
    this.value = value;
  }

  public Integer getTopNum() {
    return topNum;
  }

  public void setTopNum(Integer topNum) {
    this.topNum = topNum;
  }

  public Integer getIndegree() {
    return indegree;
  }

  public void setIndegree(Integer indegree) {
    this.indegree = indegree;
  }

  public Integer getValue() {
    return value;
  }

  public void setValue(Integer value) {
    this.value = value;
  }

}
