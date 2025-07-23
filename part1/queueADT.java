import java.util.Iterator;

/**
 * QueueADT defines the interface for a generic queue data structure.
 *
 * @param <E> the type of elements held in this queue
 */
public interface QueueADT<E> {

    /**
     * Adds an element to the rear of the queue.
     *
     * @param element the element to be added
     * @throws NullPointerException if the element is null
     * @post the element is placed at the rear of the queue
     */
    void enqueue(E element);

    /**
     * Removes and returns the front element from the queue.
     *
     * @return the element removed from the front of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     * @post the front element is removed from the queue
     */
    E dequeue();

    /**
     * Returns the front element of the queue without removing it.
     *  
     * @return the front element of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     * @post the queue remains unchanged
     */
    E peek();

    /**
     * Check if the queue is empty or not
     * 
     * @return true if the queue has no elements; otherwise, return false
     * @post the queue remains unchanged
     */
    boolean isEmpty();

    /**
     * Returns the number of elements currently in the queue
     * 
     * @return the numbers of elements in the queue
     * @post the queue remains unchanged
     */
    int size();

    /**
     * Checks if the queue contains the specified element.
     *
     * @param element the element to look for
     * @return true if the element is in the queue; false otherwise
     * @throws NullPointerException if the element is null
     * @post the queue remains unchanged
     */
    boolean contains(E element);

    /**
     * Searches for the position of the element in the queue.
     *
     * @param element the element to search for
     * @return the 1-based position from the front of the queue; -1 if not found
     * @throws NullPointerException if the element is null
     * @post the queue remains unchanged
     */
    int search(E element);

    /**
     * Removes all elements from the queue.
     *
     * @post the queue is empty
     */
    void dequeueAll();

    /**
     * Returns an array containing all of the elements in this queue from front to
     * rear.
     *
     * @return an Object array containing all elements
     * @post the queue remains unchanged
     */
    Object[] toArray();

    /**
     * Returns an array containing all of the elements in this queue from front to
     * rear,
     * using the provided array if it is large enough.
     *
     * @param holder the array into which the elements are to be stored
     * @return an array containing the elements of the queue
     * @throws NullPointerException if the provided array is null
     * @post the queue remains unchanged
     */
    E[] toArray(E[] holder);

    /**
     * Returns an iterator over the elements in the queue from front to rear.
     *
     * @return an iterator over the elements
     * @post the queue remains unchanged
     */
    Iterator<E> iterator();

    /**
     * Checks whether the queue is full (only relevant for fixed-size queues).
     *
     * @return false, since this implementation does not limit size
     * @post the queue remains unchanged
     */
    boolean isFull();

    /**
     * Checks if this queue is equal to another object.
     *
     * @param o the object to compare with
     * @return true if both queues are equal; false otherwise
     * @post the queue remains unchanged
     */
    @Override
    boolean equals(Object o);
}