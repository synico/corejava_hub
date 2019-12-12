package com.ds.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TopSort {

  private static Map<Vertex, List<Vertex>> GRAPH = new LinkedHashMap<>();

  private static List<Vertex> VERTICES = null;

  private static List<Vertex> SORTED_VERTICES = new ArrayList<>();

  private static Vertex V1, V2, V3, V4, V5, V6, V7;

  static {
    V1 = new Vertex(1);
    V2 = new Vertex(2);
    V3 = new Vertex(3);
    V4 = new Vertex(4);
    V5 = new Vertex(5);
    V6 = new Vertex(6);
    V7 = new Vertex(7);
    VERTICES.add(V1);
    VERTICES.add(V2);
    VERTICES.add(V3);
    VERTICES.add(V4);
    VERTICES.add(V5);
    VERTICES.add(V6);
    VERTICES.add(V7);
  }

  private static void initGraph() {
    List<Vertex> adjacent1 = Stream.of(V2, V4, V3).collect(Collectors.toList());
    GRAPH.put(V1, adjacent1);
    adjacent1 = Stream.of(V4, V5).collect(Collectors.toList());
    GRAPH.put(V2, adjacent1);
    adjacent1 = Stream.of(V6).collect(Collectors.toList());
    GRAPH.put(V3, adjacent1);
    adjacent1 = Stream.of(V6, V7, V3).collect(Collectors.toList());
    GRAPH.put(V4, adjacent1);
    adjacent1 = Stream.of(V4, V7).collect(Collectors.toList());
    GRAPH.put(V5, adjacent1);
    adjacent1 = null;
    GRAPH.put(V6, adjacent1);
    adjacent1 = Stream.of(V6).collect(Collectors.toList());
    GRAPH.put(V7, adjacent1);
  }

  private static void initIndegree() {
    for(Vertex v : VERTICES) {
      v.setIndegree(0);
    }
    for(Vertex v : VERTICES) {
      List<Vertex> adjacentList = GRAPH.get(v);
      if(adjacentList != null) {
        for(Vertex adjacent : adjacentList) {
          adjacent.setIndegree(adjacent.getIndegree() + 1);
        }
      }
    }
  }

  public static void topsort() throws Exception {
    for(int counter = 0; counter < VERTICES.size(); counter++) {
      //find new vertex of indegree = 0
      Vertex v = findNewVertexOfIndegreeZero(counter);
      if(v == null) {
        throw new Exception("Cycle found");
      }
      v.setTopNum(counter);
      SORTED_VERTICES.add(v);
      List<Vertex> adjacentList = GRAPH.get(v);
      if(adjacentList != null) {
        for(Vertex vertex : adjacentList) {
          vertex.setIndegree(vertex.getIndegree() - 1);
        }
      }
    }
  }

  public static Vertex findNewVertexOfIndegreeZero(int counter) {
    Vertex vertex = null;
    for(int i = counter; i < VERTICES.size(); i++) {
      int length = VERTICES.size() - counter;
      Vertex beginVertex = VERTICES.get(i);

    }
    return  vertex;
  }

  public static void main(String args[]) throws Exception {
    TopSort.initGraph();
    TopSort.initIndegree();
    TopSort.topsort();
    SORTED_VERTICES.sort(Comparator.comparing(Vertex::getTopNum));
    System.out.println("sorted graph: " + SORTED_VERTICES);
  }

}
