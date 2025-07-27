package implementations;

import utilities.StackADT;
import utilities.Iterator;
import java.util.EmptyStackException;
import utilities.ListADT;

public class MyStack<E> implements StackADT<E> {

    private ListADT<E> list;

    public MyStack() {
        list = new MyArrayList<>();
    }

    @Override
    public void push(E toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException("Cannot push null element");
        }
        list.add(toAdd); // Add to top (end of list)
    }

    @Override
    public E pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.remove(list.size() - 1); // Remove from top
    }

    @Override
    public E peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.get(list.size() - 1); // Peek at top
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(list.size() - 1 - i); // reverse order
        }
        return result;
    }

    @Override
    public E[] toArray(E[] holder) throws NullPointerException {
        if (holder == null)
            throw new NullPointerException("Null holder");

        int size = list.size();
        if (holder.length < size) {
            holder = (E[]) java.util.Arrays.copyOf(holder, size, holder.getClass());
        }

        for (int i = 0; i < size; i++) {
            holder[i] = list.get(size - 1 - i); // reverse order
        }

        if (holder.length > size) {
            holder[size] = null;
        }

        return holder;
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException {
        return list.contains(toFind);
    }

    @Override
    public int search(E toFind) throws NullPointerException {
        if (toFind == null) {
            throw new NullPointerException("Cannot search null");
        }
        // Search from top of stack (end of list)
        for (int i = list.size() - 1, pos = 1; i >= 0; i--, pos++) {
            if (toFind.equals(list.get(i))) {
                return pos; // return 1-based position from top
            }
        }
        return -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = list.size() - 1;

            @Override
            public boolean hasNext() {
                return index >= 0;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException("No more elements");
                }
                return list.get(index--); // reverse direction
            }
        };
    }

    @Override
    public boolean equals(StackADT<E> that) {
        if (that == null || this.size() != that.size())
            return false;

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
    public int size() {
        return list.size();
    }

    @Override
    public boolean stackOverflow() {
        return false; // always false for dynamic stack
    }
}