package org.csc301;

public class HeapFullException extends Exception {

	public HeapFullException() {
		super("Heap is full!");
	}

	public HeapFullException(String message) {
		super(message);
	}

}
