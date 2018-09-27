package com.nick.jdk8.annotations;

import java.util.List;

public abstract class TestTypeAnnotation<T> implements @TypeUseNotNull List<@TypeUseReadOnly T> {

    String myString = new @TypeUseNotNull String();

    public void validateValues() {

    }
}
