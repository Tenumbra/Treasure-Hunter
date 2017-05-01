package org.csc301;

public class HeapEmptyException extends Exception {

	public HeapEmptyException() {
		super("Heap is empty!");
	}

	public HeapEmptyException(String message) {
		super(message);
	}

}