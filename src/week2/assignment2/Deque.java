package week2.assignment2;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first, last;
    private int N;

    private class Node<Item> {
        Item item;
        Node<Item> next;
        Node<Item> prev;
    }

    // construct an empty deque
    public Deque(){
        first = last;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the deque
    public int size() {
        return N;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) { throw new IllegalArgumentException(); }
        Node<Item> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        if(isEmpty()) { last = first; }
        else { oldFirst.prev = first; }
        N++;

    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) { throw new IllegalArgumentException(); }
        Node<Item> oldLast = last;
        last = new Node<>();
        last.item = item;
        last.prev = oldLast;
        if(isEmpty()) { first = last; }
        else { oldLast.next = first; }
        N++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) { throw new NoSuchElementException(); }

        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) { throw new NoSuchElementException(); }

        Item item = last.item;
        last = last.prev;
        N--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator(last);
    }

    private class ListIterator implements Iterator<Item> {
        Node<Item> current;

        public ListIterator(Node last) { current = last; }

        public boolean hasNext() { return current != first.prev; }

        public void remove() {  throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) { throw new NoSuchElementException(); }
            Item item = current.item;
            current = current.prev;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        for (int i = 0; i < 10; i += 2) {
            deque.addFirst(i);
            deque.addLast(i + 1);
        }
        Iterator<Integer> it = deque.iterator();
        while (it.hasNext()) {
            StdOut.print(it.next() + " ");
        }
        StdOut.println("size: " + deque.size());

        for (int i = 0; i < 3; ++i) {
            deque.removeLast();
            deque.removeFirst();
        }
        it = deque.iterator();
        while (it.hasNext()) StdOut.print(it.next() + " ");
        StdOut.println("size: " + deque.size());

        for (int i = 0; i < 6; i += 2) {
            deque.addLast(i);
            deque.addFirst(i + 1);
        }
        it = deque.iterator();
        while (it.hasNext()) StdOut.print(it.next() + " ");
        StdOut.println(" size: " + deque.size());
    }
}