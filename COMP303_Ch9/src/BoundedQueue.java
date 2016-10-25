/**
 * 
 * A first-in, first-out bounded collection of objects
 *
 */
public class BoundedQueue<E> {
	
	private Object[] elements;
	private int head;
	private int tail;
	private int size;
	
	/**
	 * Constructs an empty queue
	 * @param capacity the maximum capacity of the queue
	 */
	public BoundedQueue(int capacity){
		elements = new Object[capacity];
		
	}
	
	/**
	 * Removes the object at the head
	 * @return the object that has been removed from the queue
	 * @throws InterruptedException
	 */
	public synchronized E remove()throws InterruptedException{
		while (size == 0){
			wait();
		}
		E r = (E) elements[head];
		head++;
		size--;
		if(head == elements.length){
			head = 0;
		}
		notifyAll();
		return r;
	}
	
	/**
	 * Appends an object at the tail
	 * @param newValue the object to be added
	 * @throws InterruptedException
	 */
	public synchronized void add(E newValue) throws InterruptedException{
		while(size == elements.length){
			wait();
		}
		elements[tail] = newValue;
		tail++;
		size++;
		if(tail == elements.length){
			tail = 0;
		}
		notifyAll();
	}
}
