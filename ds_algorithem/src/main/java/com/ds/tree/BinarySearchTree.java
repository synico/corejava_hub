package com.ds.tree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

@SuppressWarnings("all")
public class BinarySearchTree<T extends Comparable<? super T>> {

    public static class BinaryNode<E> {

        E element;

        BinaryNode<E> left;

        BinaryNode<E> right;

        BinaryNode(E theElement) {
            this(theElement, null, null);
        }

        BinaryNode(E theElement, BinaryNode<E> lt, BinaryNode<E> rt) {
            element = theElement;
            left = lt;
            right = rt;
        }
        
        public String toString() {
            return "" + element;
        }
        
        public E getElement() {
            return element;
        }
        
        public BinaryNode<E> getLeftNode() {
            return this.left;
        }
        
        public BinaryNode<E> getRightNode() {
            return this.right;
        }
    }

    private BinaryNode<T> root;
    private Comparator<? super T> cmp;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<? super T> c) {
        root = null;
        cmp = c;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(T x) {
        return contains(x, root);
    }

    public BinaryNode<T> findMin() {
        if (isEmpty())
            throw new NullPointerException();
        return findMin(root);
    }

    public BinaryNode<T> findMax() {
        if (isEmpty())
            throw new NullPointerException();
        return findMax(root);
    }

    public void insert(T x) {
        root = insert(x, root);
    }

    public void remove(T x) {
        root = remove(x, root);
    }

    public BinaryNode<T> getRoot() {
        return root;
    }
    
    private int myCompare(T lhs, T rhs) {
        if (cmp != null) {
            return cmp.compare(lhs, rhs);
        } else {
            return ((Comparable) lhs).compareTo(rhs);
        }
    }

    private boolean contains(T x, BinaryNode<T> t) {
        if (t == null) {
            return false;
        }

        int compareResult = this.myCompare(x, t.element);
        if (compareResult < 0) {
            return contains(x, t.left);
        } else if (compareResult > 0) {
            return contains(x, t.right);
        } else {
            return true;
        }
    }

    private BinaryNode<T> findMin(BinaryNode<T> t) {
        if (t == null) {
            return null;
        }
        if(t.left == null) {
            return t;
        } else {
            return findMin(t.left);
        }
    }

    private BinaryNode<T> findMax(BinaryNode<T> t) {
        if (t == null) {
            return null;
        }
        
        if(t.right == null) {
            return t;
        } else {
            return findMax(t.right);
        }
    }
    
    private BinaryNode<T> findMaxByWhile(BinaryNode<T> t) {
        if(t != null) {
            while(t.right != null) {
                t = t.right;
            }
        }
        return t;
    }

    private BinaryNode<T> insert(T x, BinaryNode<T> t) {
        if (t == null) {// empty tree
            t = new BinaryNode(x, null, null);
        } else {
            int compareResult = this.myCompare(x, t.element);
            if (compareResult < 0) {
//                if(t.left == null) {
//                    t.left = new BinaryNode(x, null, null);
//                } else {
//                    insert(x, t.left);
//                }
                t.left = insert(x, t.left);
            } else {
//                if(t.right == null) {
//                    t.right = new BinaryNode(x, null, null);
//                } else {
//                    insert(x, t.right);
//                }
                t.right = insert(x, t.right);
            }
        }
        return t;
    }

    /**
     * Internal method to remove from a subtree
     * 
     * @param x
     *            the item to remove
     * @param t
     *            the node that roots the subtree
     * @return the new root of the subtree
     */
    private BinaryNode<T> remove(T x, BinaryNode<T> t) {
        if (t == null) {
            return t;
        }

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) {
            t.left = remove(x, t.left);
        } else if (compareResult > 0) {
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) { // Two children
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else {
            t = (t.left != null) ? t.left : t.right;
        }

        return t;
    }
    
    public void printTree() {
        levelOrderTraversal(root);
    }

    
    //preorder-recursive
    private void preorderPrint(BinaryNode<T> t) {
        if(t != null) {
            System.out.println("Node: " + t.element);//1st: node
            if(t.left != null) {
                preorderPrint(t.left);//2nd: left
            }
            if(t.right != null) {
                preorderPrint(t.right);//3rd: right
            }
        }
    }
    
    //postorder-recursive
    private void postorderPrint(BinaryNode<T> t) {
        if(t != null) {
            if(t.left != null)
                postorderPrint(t.left);
            if(t.right != null)
                postorderPrint(t.right);
            System.out.println("Node: " + t.element);
        }
    }
    
    //preorder-non-recursive
    private void preOrderTraversal(BinaryNode<T> t) {
        Stack<BinaryNode> stack = new Stack<BinaryNode>();
        while(t != null || stack.size() > 0) {
            if(t != null) {
                System.out.println("Node: " + t.element);
                stack.push(t);
                t = t.left;
            } else {
                t = stack.pop();
                t = t.right;
                
            }
        }
    }
    
    private void inOrderTraversal(BinaryNode<T> t) {
        Stack<BinaryNode> stack = new Stack<BinaryNode>();
        while(t != null || stack.size() > 0) {
            if(t != null) {
                stack.push(t);
                t = t.left;
            } else {
                t = stack.pop();
                System.out.println("Node: " + t.element);
                t = t.right;
            }
        }
    }
    
    private void postOrderTraversal(BinaryNode<T> t) {
        Stack<BinaryNode> stack = new Stack<BinaryNode>();
    }
    
    private void levelOrderTraversal(BinaryNode<T> t) {
        Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
        Queue<BinaryNode> swap = new LinkedList<BinaryNode>();
        queue.offer(t);
        while(swap.size() > 0 || queue.size() > 0) {
            if(queue.size() > 0) {
                while(queue.size() > 0) {
                    BinaryNode bn = queue.poll();
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
    
    private void levelOrderPrint() {
        Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            BinaryNode<T> node = queue.poll();
            BinaryNode<T> leftNode = node.getLeftNode();
            BinaryNode<T> rightNode = node.getRightNode();
            if(leftNode != null) {
                queue.offer(leftNode);
            }
            if(rightNode != null) {
                queue.offer(rightNode);
            }
            System.out.println("Node: " + node.getElement());
        }
        
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(6);
        bst.insert(2);
        bst.insert(8);
        bst.insert(1);
        bst.insert(4);
        bst.insert(3);
//        bst.printTree();
        bst.insert(5);
//        bst.remove(6);//remove root
        //test method contains
        bst.levelOrderTraversal(bst.getRoot());
//        bst.postorderPrint(bst.getRoot());
        boolean isContains = bst.contains(14);
        System.out.println("contains element: " + isContains);
        System.out.println("min element in tree: " + bst.findMin());
        System.out.println("max element in tree: " + bst.findMax());
        System.out.println("----------------------");
        bst.levelOrderPrint();
    }

}
