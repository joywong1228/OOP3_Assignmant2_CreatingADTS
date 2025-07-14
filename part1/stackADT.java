/**
 * StackADT defines the interface for a generic stack data structure.
 *
 * @param <E> the type of elements held in this stack
 */
public interface stackADT<E> {
    /**
     * Pushed an element onto the top of the stack.
     * 
     * @param element the element to be pushed onto the stack
     * @throws NullPointerException if the element is null
     * @post the element is added to the top of the stack
     */
    void push(E element);

    /**
     * Removes and returns the top element from the stack.
     * 
     * @return the element removed from the top of the stack
     * @throws NoSuchElementException if the stack is empty
     * @post the top element is removed from the stack
     */
    E pop();

    /**
     * Returns, without removing, the top element of the stack.
     * 
     * @return the top element of the stack
     * @throws NoSuchElementException if the stack is empty
     * @post the stack remains unchanged
     */
    E peek();

    /**
     * Checks whether the stack is empty.
     * 
     * @return true if the stack is empty; false otherwise
     * @post the stack remains unchanged
     */
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