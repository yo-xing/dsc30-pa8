/**
 * TODO
 */
public class MyPriorityQueue<T extends Comparable<? super T>> {

    private BinaryHeap<T> pQueue;
    /**
     * Constructor that creates a new priority queue using binary min-heap
     * @param initialSize the given size
     */
    public MyPriorityQueue(int initialSize) {
        this.pQueue = new BinaryHeap<T>(initialSize, false);
    }

    /**
     * Inserts an element into the Priority Queue. The element received cannot
     * be null.
     *
     * @param element Element to be inserted.
     * @throws NullPointerException if the element received is null.
     * @return returns true
     */
    public boolean offer( T element ) throws NullPointerException {
        if(element == null){
            throw new NullPointerException();
        }
        this.pQueue.add(element);
        return true;
    }

    /**
     * Retrieves the head of this Priority Queue (smallest element), or null
     * if the queue is empty.
     *
     * @return The head of the queue (smallest element), or null if queue is
     *           empty.
     */
    public T poll() {
        if(this.pQueue.size() == 0){
            return null;
        }
        return this.pQueue.remove();
    }

    /**
     * Clears the contents of the queue
     */
    public void clear() {
        this.pQueue.clear();
    }

    /**
     * Retrieves, but does not remove, the head of this queue, or
     *  returns null if this queue is empty.
     * @return the next item to be removed, null if the queue is empty
     */
    public T peek() {
        if(this.pQueue.size() == 0){
            return null;
        }
        return this.pQueue.element();
    }

    /**
     * Return the number of elements in this priority queue
     * @return number of elements
     */
    public int size() {
       return this.pQueue.size();
    }

}
