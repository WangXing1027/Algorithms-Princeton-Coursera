package week2.stacks;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item> {
    private Node<Item> first;

    private class Node<Item> {
        Item item;
        Node<Item> next;
    }

    public Iterator<Item> iterator(){
        return new ListIterator(first);
    }

    private class ListIterator implements Iterator<Item> {
        Node<Item> current;

        public ListIterator(Node first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            /*not supported */
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void push(Item item) {
        Node<Item> oldFirst = first;
        first = new Node<Item>();
        first.next = oldFirst;
        first.item = item;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        return item;
    }
}
