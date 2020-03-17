package com.nick.jdk8.lambdas;

public class Study {

    public static void main(String[] args) {
        Study study = Builder.of(Study::new)
                .with(Study::setId, 1L)
                .build();
    }
}
