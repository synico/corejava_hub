package com.nick.jdk8.lambdas;

@FunctionalInterface
public interface Consumer1<T, P1> {
    void accept(T t, P1 p1);
}
