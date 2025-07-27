package implementations;

import utilities.QueueADT;
import utilities.Iterator;
import exceptions.EmptyQueueException;
import utilities.ListADT;

public class MyQueue<E> implements QueueADT<E> {

    private ListADT<E> list;

    public MyQueue() {
        list = new MyDLL<>(); // use your own implementation
    }

    @Override
    public void enqueue(E toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException("Cannot enqueue null");
        }
        list.add(toAdd); // add to the rear
    }

    @Override
    public E dequeue() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException("Queue is empty");
        }
        return list.remove(0); // remove from front
    }

    @Override
    public E peek() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException("Queue is empty");
        }
        return list.get(0); // get front without removing
    }

    @Override
    public void dequeueAll() {
        list.clear();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException {
        return list.contains(toFind);
    }

    @Override
    public int search(E toFind) throws NullPointerException {
        if (toFind == null) throw new NullPointerException("Cannot search null element");
        for (int i = 0; i < list.size(); i++) {
            if (toFind.equals(list.get(i))) return i;
        }
        return -1;
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public boolean equals(QueueADT<E> that) {
        if (that == null || that.size() != this.size()) return false;
        Iterator<E> thisIt = this.iterator();
        Iterator<E> thatIt = that.iterator();
        while (thisIt.hasNext()) {
            if (!thisIt.next().equals(thatIt.next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public E[] toArray(E[] holder) throws NullPointerException {
        return list.toArray(holder);
    }

    @Override
    public boolean isFull() {
        return false; // linked list-backed queue is never full
    }

    @Override
    public int size() {
        return list.size();
    }
}