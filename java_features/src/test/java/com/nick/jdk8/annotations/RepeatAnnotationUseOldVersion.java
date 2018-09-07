package com.nick.jdk8.annotations;

import com.nick.jdk8.annotations.Authorities;
import com.nick.jdk8.annotations.Authority;

public class RepeatAnnotationUseOldVersion {
    @Authorities({@Authority(role="admin"), @Authority(role="manager")})
    public void doSomething() {
    }
}
