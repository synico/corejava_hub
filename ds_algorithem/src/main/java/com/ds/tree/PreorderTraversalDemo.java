package com.ds.tree;

import java.io.File;

@SuppressWarnings("all")
public class PreorderTraversalDemo extends File {

    public PreorderTraversalDemo(String name) {
        super(name);
    }

    public void printName(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("----");
        }
        // System.out.println(getName() + " | " + super.getAbsolutePath());
        System.out.println(getName() + "|" + depth);
    }

    public void listAll() {
        listAll(0);
    }

    private void listAll(int depth) {
        printName(depth);

        if (isDirectory()) {
            String[] entries = list();
            for (int i = 0; i < entries.length; i++) {
                PreorderTraversalDemo child = new PreorderTraversalDemo(getPath() + separatorChar + entries[i]);
                child.listAll(depth + 1);
            }

        }
    }

    public static void main(String args[]) {
        PreorderTraversalDemo demo = new PreorderTraversalDemo("./ds_algorithem/src");
        demo.listAll();
    }

}
