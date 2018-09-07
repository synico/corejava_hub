package com.nick.jdk8.annotations;

public class RepeatAnnotationUseNewVersion {
    @NewAuthority(role="admin")
    @NewAuthority(role="manager")
    public void doSomething() {
    }
}
