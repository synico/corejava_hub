package com.nick.child;

import com.nick.object.ParentClass;

public class ChildClass extends ParentClass {

    public void checkParentMethods() {
        System.out.println(this.getClass() + ", method: checkParentMethods");
        parentProtectedMethod();
        super.parentProtectedMethod();
    }

    public static void main(String[] args) {
        ParentClass parentClass = new ParentClass();

        ChildClass childClass = new ChildClass();
        childClass.parentProtectedMethod();
    }
}
