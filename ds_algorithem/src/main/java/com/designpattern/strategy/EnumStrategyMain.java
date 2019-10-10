package com.designpattern.strategy;

public class EnumStrategyMain {

  public static void main(String args[]) {
    EnumStrategy strategy = EnumStrategy.valueOf("FAST");
    strategy.run();
  }
}
