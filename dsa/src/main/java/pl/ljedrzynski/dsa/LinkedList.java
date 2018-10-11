package pl.ljedrzynski.dsa;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

public class LinkedList<T> implements Iterable<T> {
    public static class Node<T> {
        T value;
        Node<T> next;

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }
    }

    protected Node<T> first;
    protected Node<T> last;
    protected int length = 0;

    public Node<T> appendLast(T value) {
        Node<T> node = getNewNode();
        node.value = value;
        if (last != null) {
            last.next = node;
        }
        last = node;
        if (first == null) {
            first = node;
        }
        length++;
        return node;
    }

    public Node<T> appendFirst(T value) {
        Node<T> node = getNewNode();
        node.value = value;
        node.next = first;
        first = node;
        length++;
        return node;
    }

    public Node<T> insert(int index, T value) {
        Node<T> node = getNewNode();
        if (index < 0 || index > length) {
            throw new IllegalArgumentException("Invalid index for insertion ");
        } else if (index == length) {
            return appendLast(value);
        } else if (index == 0) {
            return appendFirst(value);
        } else {
            Node result = first;
            while (index > 1) {
                result = result.next;
                index--;
            }
            node.value = value;
            node.next = result.next;
            result.next = node;
            length++;
            return node;
        }
    }

    protected Node<T> getNewNode() {
        return new Node<>();
    }

    public T findAtIndex(int index) {
        Node result = first;
        while (index >= 0) {
            if (result == null) {
                throw new NoSuchElementException();
            } else if (index == 0) {
                return first.value;
            } else if (index == length) {
                return last.value;
            }
            result = result.next;
            index--;
        }
        return null;
    }

    public T getFirst() {
        if (length <= 0) {
            return null;
        }
        return first.value;
    }


    public Node<T> removeFirst() {
        if (length == 0) {
            throw new NoSuchElementException();
        }
        Node<T> result = first;
        first = result.next;
        length--;
        if (length == 0) {
            last = null;
        }
        return result;
    }

    public Node<T> removeAtIndex(int index) {
        if (index >= length || index < 0) {
            throw new NoSuchElementException();
        }
        if (index == 0) {
            return removeFirst();
        }
        Node justBeforeIt = first;
        while (--index >= 1) {
            justBeforeIt = justBeforeIt.next;
        }
        Node<T> removed = justBeforeIt.next;
        if (justBeforeIt.next == last) {
            last = justBeforeIt;
        }
        justBeforeIt.next = justBeforeIt.next.next;
        length--;
        return removed;
    }

    public int getLength() {
        return length;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            protected Node<T> next = first;
            protected Node<T> lastReturned;
            protected Node<T> previous;

            @Override
            public boolean hasNext() {
                return next != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new IllegalStateException();
                }
                Node<T> next = this.next;
                this.next = next.next;
                this.previous = this.lastReturned;
                this.lastReturned = next;
                return next.value;
            }

            @Override
            public void remove() {
                if (length == 0) {
                    throw new IllegalStateException();
                }
                Node<T> toRemove = this.lastReturned;
                if (toRemove == last) {
                    last = previous;
                }
                if (toRemove == first) {
                    first = toRemove.next;
                }
                if (previous != null) {
                    previous.next = toRemove.next;
                }
                length--;
            }
        };
    }

    public void forEach(Consumer<? super T> action) {
        if (length == 0) {
            throw new NoSuchElementException();
        }
        int length = this.length;
        Node<T> node = first;
        while (--length >= 0) {
            action.accept(node.value);
            node = node.next;
        }
    }

    public Spliterator<T> spliterator() {
        return null;
    }
}
