/**
 * QueueADT defines the interface for a generic queue data structure.
 *
 * @param <E> the type of elements held in this queue
 */
public interface queueADT<E> {

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

    E peek();

    boolean isEmpty();

    int size();

    E front();

    void clear();

    boolean contains(E element);

}