package pl.ljedrzynski.dsa;

import java.util.NoSuchElementException;

public class DoublyLinkedList<T> extends LinkedList<T> {
    protected static class DoublyLinkedNode<T> extends Node<T> {
        protected DoublyLinkedNode<T> prev;
    }

    @Override
    protected Node<T> getNewNode() {
        return new DoublyLinkedNode<>();
    }

    @Override
    public Node<T> appendFirst(T value) {
        Node<T> first = super.appendFirst(value);
        if (first.next != null) {
            ((DoublyLinkedNode) first.next).prev = (DoublyLinkedNode) first;
        }
        return first;
    }

    @Override
    public Node<T> appendLast(T value) {
        DoublyLinkedNode<T> orgLast = (DoublyLinkedNode<T>) last;
        Node<T> last = super.appendLast(value);
        ((DoublyLinkedNode) last).prev = orgLast;
        return last;
    }

    @Override
    public Node<T> insert(int index, T value) {
        DoublyLinkedNode<T> inserted = (DoublyLinkedNode<T>) super.insert(index, value);
        if (index != 0 && index != length) {
            if (inserted.next != null) {
                inserted.prev = ((DoublyLinkedNode<T>)
                        inserted.next).prev;
                ((DoublyLinkedNode<T>) inserted.next).prev = inserted;
            }
        }
        return inserted;
    }

    @Override
    public Node<T> removeFirst() {
        Node removed = super.removeFirst();
        if (first != null) {
            ((DoublyLinkedNode<T>) first).prev = null;
        }
        return removed;
    }

    @Override
    public Node<T> removeAtIndex(int index) {
        if (index < 0 || index >= length) {
            throw new NoSuchElementException();
        }
        if (index == length - 1) {
            return removeLast();
        }
        DoublyLinkedNode<T> nodeRemoved
                = (DoublyLinkedNode<T>) super.removeAtIndex(index);
        if (nodeRemoved.next != null) {
            ((DoublyLinkedNode) nodeRemoved.next).prev = nodeRemoved.prev;
        }
        return nodeRemoved;
    }

    private Node<T> removeLast() {
        Node<T> orgLast = last;
        if (last == null) {
            throw new IllegalStateException("Cannot remove last element. List is empty!");
        }
        last = ((DoublyLinkedNode<T>) orgLast).prev;
        if (last != null) {
            last.next = null;
        } else {
            first = null;
        }
        length--;
        return orgLast;
    }

}
