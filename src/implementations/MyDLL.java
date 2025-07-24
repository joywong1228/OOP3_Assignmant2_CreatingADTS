package implementations;

import utilities.ListADT;

import java.util.NoSuchElementException;

import utilities.Iterator;

public class MyDLL<E> implements ListADT<E> {

    private MyDLLNode<E> head;
    private int size;

    public MyDLL() {
        head = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }
    @Override
    public void clear() {
        head = null;
        size = 0;
    }
    @Override
    public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
        if (toAdd == null) {
            throw new NullPointerException("Cannot add null element");
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            MyDLLNode<E> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
        return true;
    }
    @Override
    public boolean add(E toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException("Cannot add null element");
        }
        MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);
        if (head == null) {
            head = newNode;
        } else {
            MyDLLNode<E> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        return true;
    }
    @Override
    public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException("Cannot add null list");
        }
        for (int i = 0; i < toAdd.size(); i++) {
            add(toAdd.get(i));
        }
        return true;
    }
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        MyDLLNode<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }
    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        E removedData = null;
        if (index == 0) {
            removedData = head.data;
            head = head.next;
        } else {
            MyDLLNode<E> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            removedData = current.next.data;
            current.next = current.next.next;
        }
        size--;
        return removedData;
    }
    @Override
    public E remove(E toRemove) throws NullPointerException {
        if (toRemove == null) {
            throw new NullPointerException("Cannot remove null element");
        }
        MyDLLNode<E> current = head;
        int index = 0;
        E removedData = null;
        while (current != null) {
            if (current.data.equals(toRemove)) {
                removedData = remove(index);
                break;
            }
            current = current.next;
            index++;
        }
        return removedData; // Element not found
    }
    @Override
    public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
        if (toChange == null) {
            throw new NullPointerException("Cannot set null element");
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        MyDLLNode<E> current = head;
        E oldData = null;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        oldData = current.data;
        current.data = toChange;
        return oldData;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    @Override
    public boolean contains(E toFind) throws NullPointerException {
        if (toFind == null) {
            throw new NullPointerException("Cannot find null element");
        }
        MyDLLNode<E> current = head;
        while (current != null) {
            if (current.data.equals(toFind)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    @Override
    public E[] toArray(E[] toHold) throws NullPointerException {
        if (toHold == null) {
            throw new NullPointerException("Cannot convert to null array");
        }
        MyDLLNode<E> current = head;
        for (int i = 0; i < size; i++) {
            toHold[i] = current.data;
            current = current.next;
        }
        return toHold;
    }
    @Override
    public E[] toArray() {
        E[] array = (E[]) new Object[10000];
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
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements");
                }
                E data = current.data;
                current = current.next;
                index++;
                return data;
            }
        };
    }

    
}
