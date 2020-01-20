package week2.assignment2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] s;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        this.size = 0;
        this.s = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item ==null) { throw new IllegalArgumentException(); }
        if (size >= s.length) { resize(2*s.length); }

        s[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if(isEmpty()) { throw new NoSuchElementException(); }

        if(size >= 0 && size <= s.length/4) { resize(s.length/2); }
        int random = StdRandom.uniform(size);
        Item item = s[random];
        s[random] = s[--size];
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if(isEmpty()) { throw new NoSuchElementException(); }
        int random = StdRandom.uniform(size);
        return s[random];
    }

    private void resize(int capacity) {
        assert capacity >= size;

        Item[] tmpArr = (Item[]) new Object[capacity];
        System.arraycopy(s, 0, tmpArr, 0, size);
        s = tmpArr;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int[] randomIndex;
        private int current;

        public RandomizedQueueIterator() {
            randomIndex = new int[size];
            for (int i = 0; i < size; ++i) {
                randomIndex[i] = i;
            }
            StdRandom.shuffle(randomIndex);
            current = 0;
        }

        public boolean hasNext() { return current != randomIndex.length; }

        public void remove() { throw new UnsupportedOperationException(); }

        public Item next() {
            if (!hasNext()) { throw new NoSuchElementException(); }
            return s[randomIndex[current++]];
        }
    }

    // unit testing (required)
    public static void main(String[] args){
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        for (int i = 0; i < 10; ++i)
            rq.enqueue(i);
        Iterator<Integer> it = rq.iterator();
        while (it.hasNext()) StdOut.print(it.next() + " ");
        StdOut.println(" size: " + rq.size());
        it = rq.iterator();
        while (it.hasNext()) StdOut.print(it.next() + " ");
        StdOut.println(" size: " + rq.size());

        for (int j = 0; j < 3; ++j) {
            for (int i = 0; i < 3; ++i)
                rq.dequeue();
            it = rq.iterator();
            while (it.hasNext()) StdOut.print(it.next() + " ");
            StdOut.println(" size: " + rq.size());
        }
    }

}