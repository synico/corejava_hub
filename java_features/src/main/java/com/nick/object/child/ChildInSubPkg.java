package com.nick.object.child;

import com.nick.object.ParentClass;

public class ChildInSubPkg extends ParentClass {

    public void checkParentMethods() {
        super.parentProtectedMethod();
    }

    public static void main(String[] args) {
        ParentClass parentClass = new ParentClass();

        ChildInSubPkg childInSubPkg = new ChildInSubPkg();
        childInSubPkg.parentProtectedMethod();
    }

}
