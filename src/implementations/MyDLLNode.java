package implementations;

public class MyDLLNode<E> {
    public E data;
    public MyDLLNode<E> next;

    public MyDLLNode(E data) {
        this.data = data;
        this.next = null;
    }
    
}
