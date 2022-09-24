//Author: Jonny Chen
//Worked with: Jerome Washika, Cole Winkhart
//Date: 12/3/2021
//Instructor: Stahr
import java.util.Arrays;
import java.util.NoSuchElementException;

public class HeapPriorityQueue<T extends Comparable<? super T>> implements PriorityQueueInterface<T> {
	private int size;
	private T[] elements;
	private static final int DEFAULT_CAPACITY = 10;
	
	public HeapPriorityQueue() {
		this(DEFAULT_CAPACITY);
	}
	
	public HeapPriorityQueue(int initialCapacity) {
		elements = (T[]) new Comparable[initialCapacity + 1];
		size = 0;
	}
	
	public HeapPriorityQueue(T[] entries) {
		this(entries.length);
		size = entries.length;
		
		for(int index = 0; index < entries.length; index++)
			elements[index + 1] = entries[index];
		for(int rIndex = size / 2; rIndex > 0; rIndex--)
			reheapDown(rIndex);
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean isFull() {
		return size == elements.length - 1;
	}

	@Override
	public void clear() {
		Arrays.fill(elements, 0, size, null);
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void add(T newEntry) {
		verifyCapacity();
		elements[++size] = newEntry;
		reheapUp(size);
	}
	
	private void reheapUp(int index) {
		if(index < 2) {
			return;
		}
		
		int parentIndex = index / 2;
		while(parentIndex > 0 && elements[parentIndex].compareTo(elements[index]) > 0) {
			swap(index, parentIndex);
			index = parentIndex;
			parentIndex = index / 2;
		}
	}
	
	private void swap(int index1, int index2) {
		T tmp = elements[index1];
		elements[index1] = elements[index2];
		elements[index2] = tmp;
	}
	
	@Override
	public T peek() {
		return(isEmpty() ? null : elements[1]);
	}

	@Override
	public T remove() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		
		T ret = elements[1];
		elements[1] = elements[size];
		elements[size--] = null;
		reheapDown(1);
		return ret;
	}
	
	private void reheapDown(int index) {
		if(size == 1) {
			return;
		}
		int parentIndex = index;
		int leftIndex = 2 * parentIndex;
		int rightIndex = 2 * parentIndex + 1;
		
		while(leftIndex <= size) {
			int maxChildIndex = findMaxChildIndex(leftIndex, rightIndex);
			swap(parentIndex, maxChildIndex);
			
			parentIndex = maxChildIndex;
			leftIndex = 2 * parentIndex;
			rightIndex = 2 * parentIndex + 1;
		}
		
	}
	
	private int findMaxChildIndex(int leftIndex, int rightIndex) {
		if(elements[rightIndex] == null) {
			return leftIndex;
		}
		if(elements[rightIndex].compareTo(elements[leftIndex]) > 0) {
			return leftIndex;
		}
		return rightIndex;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(elements);
	}
	
	public void verifyCapacity() {
		if(size == elements.length - 1) {
			elements = Arrays.copyOf(elements, 2 * size + 1);
		}
	}
}

