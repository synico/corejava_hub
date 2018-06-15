package com.ds.list;

import java.util.Iterator;

public class MyLinkedList<E> implements Iterable<E> {

    private Node<E> head;

    private Node<E> tail;

    private int size;

    private static class Node<E> {
        private E data;
        private Node<E> prev;
        private Node<E> next;

        Node(Node<E> prev, E data, Node<E> next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }

    private class LinkedListIterator implements Iterator<E> {

        private Node<E> current = head.next;

        @Override
        public boolean hasNext() {
            return (current != tail);
        }

        @Override
        public E next() {
            return current.data;
        }
    }

    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return (size() == 0);
    }

}
