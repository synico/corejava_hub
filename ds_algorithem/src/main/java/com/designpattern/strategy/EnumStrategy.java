package com.designpattern.strategy;

public enum EnumStrategy {
  FAST {
    @Override
    void run() {
      System.out.println("Fast");
    }
  },
  NORMAL {
    @Override
    void run() {
      System.out.println("NORMAL");
    }
  },
  SMOOTH {
    @Override
    void run() {
      System.out.println("SMOOTH");
    }
  },
  SLOW {
    @Override
    void run() {
      System.out.println("SLOW");
    }
  };
  abstract void run();
}
