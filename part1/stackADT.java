/**
 * StackADT defines the interface for a generic stack data structure.
 *
 * @param <E> the type of elements held in this stack
 */
public interface stackADT<E> {

    void push(E element);

    E pop();

    E peek();

    boolean isEmpty();

    /**
     * Returns the number of elements in the stack.
     *
     * @return the number of elements in the stack
     * @post the stack remains unchanged
     */
    int size();

    void clear();

    boolean contains(E element);

}