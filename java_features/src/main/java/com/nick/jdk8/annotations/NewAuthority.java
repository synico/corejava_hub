package com.nick.jdk8.annotations;

import java.lang.annotation.Repeatable;

@Repeatable(NewAuthorities.class)
public @interface NewAuthority {
    String role();
}
