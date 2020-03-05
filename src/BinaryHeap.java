import java.util.NoSuchElementException;
/**
 * Yo Jeremijenko-Conley, A14454136
 * @param <T> Generics
 */
public class BinaryHeap <T extends Comparable<? super T>> {

    private static final int DEFAULT_CAPACITY = 5; // default initial capacity
    private static final int EXPAND_FACTOR    = 2; // resizing factor

    private T[] heap;          // heap array
    private int nelems;        // number of elements
    private boolean isMaxHeap; // boolean to indicate whether heap is max or min
    public int two = 2;

    /**
     * Initializes a binary max heap with capacity = 5
     */
    @SuppressWarnings("unchecked")
    public BinaryHeap() {
        this.heap = (T[]) new Comparable[DEFAULT_CAPACITY];
        this.isMaxHeap = true;
    }
    /**
     * Initializes a binary max heap with a given initial capacity.
     *
     * @param heapSize The initial capacity of the heap.
     */
    @SuppressWarnings("unchecked")
    public BinaryHeap(int heapSize) {
        this.heap = (T[]) new Comparable[heapSize];
        this.nelems = 0;
        this.isMaxHeap = true;
    }

    /**
     * Initializes a binary heap with a given initial capacity.
     * @param heapSize The initial capacity of the heap.
     * @param isMaxHeap indicates whether the heap should be max or min
     */
    @SuppressWarnings( "unchecked" )
    public BinaryHeap(int heapSize, boolean isMaxHeap) {
        this.isMaxHeap = isMaxHeap;
        this.heap = (T[]) new Comparable[heapSize];
        this.nelems = 0;
    }

    /**
     * Returns the number of elements stored in the heap.
     *
     * @return The number of elements stored in the heap.
     */
    public int size() {
        return this.nelems;
    }

    /**
     * Clears all the items in the heap
     * Heap will be empty after this call returns
     */
    @SuppressWarnings("unchecked")
    public void clear() {
        this.nelems = 0;
        this.heap = (T[]) new Comparable[this.heap.length];
    }

    /**
     * Adds the specified element to the heap; data cannot be null.
     * Resizes the storage if full.
     * @param data The element to add.
     * @throws NullPointerException if o is null.
     */
    public void add( T data ) throws NullPointerException {
        if(data == null){
            throw new NullPointerException();
        }
        if(this.size() >= this.heap.length){
            this.resize();
        }
        this.heap[this.size()] = data;
        bubbleUp(this.size());
        ++ this.nelems;
    }

    /**
     * Removes and returns the element at the root. If the
     * heap is empty, then this method throws a NoSuchElementException.
     * @return The element at the root stored in the heap.
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T remove() throws NoSuchElementException {
        if(this.size() == 0){
            throw new NoSuchElementException();
        }
        T temp = this.heap[0];
        this.heap[0] = this.heap[this.size() - 1];
        this.heap[this.size() - 1] = null;
        -- this.nelems;
        this.trickleDown(0);
        return temp;
    }

    /**
     * Retrieves, but does not remove, the element at the root.
     * @return item at the root of the heap
     * @throws NoSuchElementException - if this heap is empty
     */
    public T element() throws NoSuchElementException {
        if(this.size() == 0){
            throw new NoSuchElementException();
        }
        return this.heap[0];
    }

    /**
     * Bubble up the element until the ordering property of the heap
     * is satisfied
     * @param index the index of the element to be trickled down
     */
    private void bubbleUp(int index) {
        if(index == 0){
            return;
        }
        int parent;
        if(index%2 == 0){
            parent = (index - 2)/two;
        }
        else{
            parent = (index - 1)/two;
        }
        if((this.heap[index].compareTo(this.heap[parent]) > 0) == this.isMaxHeap){
            T temp = this.heap[parent];
            this.heap[parent] = this.heap[index];
            this.heap[index] = temp;
            this.bubbleUp(parent);
        }
    }

    /**
     * Trickle down the element until the ordering property of the heap
     * is satisfied
     * @param index the index of the element to be trickled down
     */
    private void trickleDown(int index) {
        int child1 = index*two + 1;
        int child2 = index*two + 2;
        int child;
        if(child1 > this.size() - 1){
            return;
        }
        if(child2 > this.size() - 1){
            child = child1;
        }
        else if((this.heap[child1].compareTo(this.heap[child2]) >= 0) == this.isMaxHeap){
            child = child1;
        }
        else{
            child = child2;
        }
        if((this.heap[index].compareTo(this.heap[child]) < 0) == this.isMaxHeap){
            T temp = this.heap[child];
            this.heap[child] = this.heap[index];
            this.heap[index] = temp;
            this.trickleDown(child);
        }

    }

    /**
     * Double the size of the heap
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        T[] bigger = (T[]) new Comparable[this.heap.length* two];
        System.arraycopy(this.heap, 0, bigger, 0, this.heap.length);
        this.heap = bigger;
    }

}
