package implementations;

import utilities.ListADT;
import utilities.Iterator;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class MyDLL<E> implements ListADT<E> {

    private MyDLLNode<E> head;
    private MyDLLNode<E> tail;
    private int size;

    public MyDLL() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
        if (toAdd == null)
            throw new NullPointerException("Cannot add null element");
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Invalid index");

        MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);

        if (index == 0) {
            newNode.next = head;
            if (head != null)
                head.prev = newNode;
            head = newNode;
            if (size == 0)
                tail = newNode;
        } else if (index == size) {
            newNode.prev = tail;
            if (tail != null)
                tail.next = newNode;
            tail = newNode;
            if (size == 0)
                head = newNode;
        } else {
            MyDLLNode<E> current = getNodeAt(index);
            MyDLLNode<E> prevNode = current.prev;
            prevNode.next = newNode;
            newNode.prev = prevNode;
            newNode.next = current;
            current.prev = newNode;
        }

        size++;
        return true;
    }

    @Override
    public boolean add(E toAdd) throws NullPointerException {
        return add(size, toAdd); // add to end
    }

    @Override
    public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
        if (toAdd == null)
            throw new NullPointerException("Cannot add null list");
        for (int i = 0; i < toAdd.size(); i++) {
            add(toAdd.get(i));
        }
        return true;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        return getNodeAt(index).data;
    }

    private MyDLLNode<E> getNodeAt(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Invalid index");
        MyDLLNode<E> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++)
                current = current.next;
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--)
                current = current.prev;
        }
        return current;
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        MyDLLNode<E> toRemove = getNodeAt(index);
        E data = toRemove.data;

        if (toRemove.prev != null) {
            toRemove.prev.next = toRemove.next;
        } else {
            head = toRemove.next;
        }

        if (toRemove.next != null) {
            toRemove.next.prev = toRemove.prev;
        } else {
            tail = toRemove.prev;
        }

        size--;
        return data;
    }

    @Override
    public E remove(E toRemove) throws NullPointerException {
        if (toRemove == null)
            throw new NullPointerException("Cannot remove null element");
        MyDLLNode<E> current = head;
        int index = 0;
        while (current != null) {
            if (current.data.equals(toRemove)) {
                return remove(index);
            }
            current = current.next;
            index++;
        }
        return null;
    }

    @Override
    public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
        if (toChange == null)
            throw new NullPointerException("Cannot set null element");
        MyDLLNode<E> node = getNodeAt(index);
        E old = node.data;
        node.data = toChange;
        return old;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException {
        if (toFind == null)
            throw new NullPointerException("Cannot search for null");
        MyDLLNode<E> current = head;
        while (current != null) {
            if (current.data.equals(toFind))
                return true;
            current = current.next;
        }
        return false;
    }

    @Override
    public E[] toArray(E[] toHold) {
        if (toHold == null) {
            throw new NullPointerException();
        }

        if (toHold.length < size) {
            // Return a new array of the same type as toHold
            return (E[]) Arrays.copyOf(this.toArray(), size, toHold.getClass());
        }

        // Copy contents manually
        MyDLLNode<E> current = head;
        for (int i = 0; i < size; i++) {
            toHold[i] = current.data;
            current = current.next;
        }

        if (toHold.length > size) {
            toHold[size] = null; // Per spec
        }

        return toHold;
    }

    @Override
    public E[] toArray() {
        E[] array = (E[]) new Object[size];
        MyDLLNode<E> current = head;
        for (int i = 0; i < size; i++) {
            array[i] = current.data;
            current = current.next;
        }
        return array;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private MyDLLNode<E> current = head;
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                if (!hasNext())
                    throw new NoSuchElementException("No more elements");
                E data = current.data;
                current = current.next;
                index++;
                return data;
            }
        };
    }
}