package com.nick.object;

/**
 * @author nick
 * @date 2020-05-08
 */

public class ParentClass {

    protected void parentProtectedMethod() {
        System.out.println(this.getClass() + ", method: parentProtectedMethod");
    }

    protected static void parentStaticProtectedMethod() {
        System.out.println(ParentClass.class.getName() + ", method: parentStaticProtectedMethod");
    }

    void parentFriendlyMethod() {
        System.out.println(this.getClass() + ", method: parentFriendlyMethod");
    }

    private void parentPrivateMethod() {
        System.out.println(this.getClass() + ", method: parentPrivateMethod");
    }

}
