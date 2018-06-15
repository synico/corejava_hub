package com.ds.tree;

public class BinaryHeap {
    
    private static final int INIT_CAPACITY = 10;
    
    private int currentSize = 0;
    private int [] array;
    
    public BinaryHeap() {
        array = new int[INIT_CAPACITY];
        currentSize = array.length;
    }
    
    public BinaryHeap(int capacity) {
        array = new int[capacity];
        currentSize = array.length;
    }
    
    public BinaryHeap(int [] items) {
        this.array = new int[items.length + 1];
        for(int i = 0; i < items.length; i++) {
            array[i + 1] = items[i];
        }
        currentSize = array.length;
    }
    
    public boolean isEmpty() {
        return false;
    }
    
    public void makeEmpty() {
        
    }
    
    public boolean insert(int x) {
        if(currentSize == array.length) {
            enlargeArray(array.length * 2 + 1);
        }
        int hole = currentSize;
        currentSize = currentSize + 1;
        while(hole/2 > 1 && array[hole/2] > x) {
            array[hole] = array[hole/2];
            hole = hole/2;
        }
        array[hole] = x;
        return true;
    }
    
    public int deleteMin() {
        return 0;
    }
    
    private void precolateDown(int hole) {
        
    }
    
    private void precolateUp(int hole) {
        
    }
    
    private void buildHeap() {
        
    }
    
    private boolean enlargeArray(int newSize) {
        int [] temp = new int[newSize];
        for(int i = 1; i < array.length; i++) {
            temp[i] = array[i];
        }
        this.array = temp;
        return true;
    }
    
    public void printBinaryHeap() {
        for(int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int [] initHeap = {13, 21, 16, 24, 31, 19, 68, 65, 26, 32};
        BinaryHeap bh = new BinaryHeap(initHeap);
        bh.printBinaryHeap();
        bh.insert(14);
        bh.printBinaryHeap();
    }

}
