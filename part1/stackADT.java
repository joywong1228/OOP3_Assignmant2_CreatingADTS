import java.util.Iterator;

/**
 * StackADT defines the interface for a generic stack data structure.
 *
 * @param <E> the type of elements held in this stack
 */
public interface StackADT<E> {
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
    
    /**
     * Checks if the stack contains the specified element.
     *
     * @param element the element to look for
     * @return true if the element is in the stack; false otherwise
     * @throws NullPointerException if the element is null
     * @post the stack remains unchanged
     */
    boolean contains(E element);

    /**
     * Searches for the position of the element in the stack.
     *
     * @param element the element to search for
     * @return the 1-based position from the top of the stack; -1 if not found
     * @throws NullPointerException if the element is null
     * @post the stack remains unchanged
     */
    int search(E element);

    /**
     * Removes all elements from the stack.
     *
     * @post the stack is empty
     */
    void clear();

    /**
     * 
     * Returns an array containing all of the elements in this stack from top to bottom.
     *
     * @return an Object array containing all elements
     * @post the stack remains unchanged
     */
    Object[] toArray();

    /**
     * 
     * Returns an array containing all of the elements in this stack from top to bottom,
     * using the provided array if it is large enough.
     *
     * @param holder the array into which the elements are to be stored
     * @return an array containing the elements of the stack
     * @throws NullPointerException if the provided array is null
     * @post the stack remains unchanged
     */
    E[] toArray(E[] holder);

    /**
     * Returns an iterator over the elements in the stack from top to bottom.
     *
     * @return an iterator over the elements
     * @post the stack remains unchanged
     */
    Iterator<E> iterator();

    /**
     * Checks whether the stack is full (only for fixed-size stacks).
     *
     * @return false, since this implementation does not limit size
     * @post the stack remains unchanged
     */
    boolean stackOverflow();

    /**
     * Checks if this stack is equal to another object.
     *
     * @param o the object to compare with
     * @return true if both stacks are equal; false otherwise
     * @post the stack remains unchanged
     */
    @Override
    boolean equals(Object o);
}