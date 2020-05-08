package com.nick.object;

public class InSamePkgClass {

    public static void main(String[] args) {
        ParentClass parentClass = new ParentClass();
        parentClass.parentProtectedMethod();
        parentClass.parentFriendlyMethod();
    }
}
