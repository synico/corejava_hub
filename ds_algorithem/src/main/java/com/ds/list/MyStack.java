package com.ds.list;

public class MyStack {
    
    int [] stack;
    
    int top = 0;
    
    public MyStack(int length) {
        stack = new int[length];
    }
    
    public void createStack(int length) {
        stack = new int[length];
    }
    
    public boolean isEmpty() {
        if(top == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean push(int e) {
        if(top < stack.length) {
            stack[top] = e;
            top++;
            return true;
        } else {
            return false;
        }
    }
    
    public int pop() {
        if(isEmpty()) {
            return 0;
        } else {
            if(top >= stack.length) {
                top = stack.length - 1;
            }
            int e = stack[top];
            top--;
            return e;
        }
    }

    public static void main(String[] args) {
        MyStack ms = new MyStack(5);
        ms.push(15);
        ms.push(6);
        ms.push(2);
        ms.push(9);
        System.out.println(ms.push(17));
        System.out.println(ms.push(3));
        System.out.println(ms.pop());
    }

}
