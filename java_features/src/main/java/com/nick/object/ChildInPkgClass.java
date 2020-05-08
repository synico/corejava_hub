package com.nick.object;

public class ChildInPkgClass extends ParentClass {

    public void testParentMethods() {
        super.parentProtectedMethod();
        super.parentFriendlyMethod();
    }

    public static void main(String[] args) {
        ParentClass parentClass = new ParentClass();
        parentClass.parentFriendlyMethod();
        parentClass.parentProtectedMethod();
    }

}
