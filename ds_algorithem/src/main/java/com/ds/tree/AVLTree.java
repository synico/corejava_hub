package com.ds.tree;

import java.util.LinkedList;
import java.util.Queue;

public class AVLTree {
    
    class AVLNode<T> {
        T element;
        AVLNode<T> left;
        AVLNode<T> right;
        int height;
        
        AVLNode(T theElement) {
            this(theElement, null, null);
        }
        
        AVLNode(T element, AVLNode<T> leftNode, AVLNode<T> rightNode) {
            this.element = element;
            this.left = leftNode;
            this.right = rightNode;
            this.height = 0;
        }
        
        public String toString() {
            return "" + element;
        }
    }
    
    public <T extends Comparable<?>> AVLNode<T> insert(T val, AVLNode<T> root) {
        if(root == null) {
            root = new AVLNode<T>(val);
        } else {
            if(this.myCompareTo(val, root.element) < 0) {
                root.left = insert(val, root.left);
            } else {
                root.right = insert(val, root.right);
            }
            root.height = root.height + 1;
        }
        return balance(root);
    }
    
    private <T> AVLNode<T> balance(AVLNode<T> node) {
        if(node == null) {
            return node;
        }
        int lrDiff = height(node.left) - height(node.right);
        if(lrDiff > 1) {
            if(height(node.left.left) >= height(node.left.right)) {
                node = rotateWithLeftChild(node);
            } else {
                doubleWithLeftChild(node);
            }
        }
        int rlDiff = height(node.right) - height(node.left);
        if(rlDiff > 1) {
            if(height(node.right.right) >= height(node.right.left)) {
                node = rotateWithRightChild(node);
            } else {
                doubleWithRightChild(node);
            }
        }
        return node;
    }
    
    //TURN LEFT
    private <T> AVLNode<T> rotateWithRightChild(AVLNode<T> node) {
        AVLNode<T> rightChild = node.right;
        node.right = rightChild.left;
        rightChild.left = node;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        rightChild.height = Math.max(height(node), height(rightChild.right)) + 1;
        return rightChild;
    }
    
    //TURN RIGHT
    private <T> AVLNode<T> rotateWithLeftChild(AVLNode<T> node) {
        AVLNode<T> leftChild = node.left;
        node.left = leftChild.right;
        leftChild.right = node;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        leftChild.height = Math.max(height(leftChild.left), height(node)) + 1;
        return leftChild;
    }
    
    //TRUN RIGTH, THEN TURN LEFT
    private <T> AVLNode<T> doubleWithLeftChild(AVLNode<T> node) {
        node.left = rotateWithRightChild(node.left);
        return rotateWithLeftChild(node);
    }
    
    //TURN LEFT, THEN TURN RIGHT
    private <T> AVLNode<T> doubleWithRightChild(AVLNode<T> node) {
        node.right = rotateWithLeftChild(node.right);
        return rotateWithRightChild(node);
    }
    
    private <T> int height(AVLNode<T> node) {
        return node == null ? -1 : node.height;
    }
    
    @SuppressWarnings("all")
    private <T> int myCompareTo(T lv, T rv) {
        return ((Comparable<T>)lv).compareTo(rv);
    }
    
    public <T> void printTree(AVLNode<T> root) {
        levelOrderTraversal(root);
    }
    
    private <T> void levelOrderTraversal(AVLNode<T> t) {
        Queue<AVLNode<T>> queue = new LinkedList<AVLNode<T>>();
        Queue<AVLNode<T>> swap = new LinkedList<AVLNode<T>>();
        queue.offer(t);
        while(t != null || queue.size() > 0) {
            if(queue.size() > 0) {
                while(queue.size() > 0) {
                    AVLNode<T> bn = queue.poll();
                    if(bn.left != null) {
                        swap.offer(bn.left);
                    } 
                    if(bn.right != null) {
                        swap.offer(bn.right);
                    }
                    System.out.print("Node: " + bn.element + " || ");
                }
                System.out.println();
                System.out.println("---------------------------------");
            } else {
                if(swap.size() > 0) {
                    while(swap.size() > 0) {
                        queue.offer(swap.poll());
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        AVLNode<Integer> root = avlTree.insert(3, null);
        root = avlTree.insert(2, root);
        root = avlTree.insert(1, root);
        root = avlTree.insert(4, root);
        root = avlTree.insert(5, root);
        root = avlTree.insert(6, root);
        root = avlTree.insert(7, root);
        avlTree.printTree(root);
    }

}
