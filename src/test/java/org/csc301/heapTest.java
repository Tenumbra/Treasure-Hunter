package org.csc301;

import junit.framework.TestCase;

public class heapTest extends TestCase {
	Heap<HeapItem> testSet = new Heap<HeapItem>(2);

	// Testing a normal sortUp function
	public void testUp() throws HeapFullException {
		testSet.add(new Node(true, 0, 0));
		testSet.add(new Node(true, 1, 1));
		((Node) testSet.items[0]).gCost = 10;
		((Node) testSet.items[0]).hCost = 10;
		((Node) testSet.items[1]).gCost = 1;
		((Node) testSet.items[1]).hCost = 1;
		assertFalse(testSet.higherPriority(testSet.items[0], testSet.items[1]));
		testSet.updateItem(testSet.items[1]); //this will call sortUp() with the same argument
		assertTrue(testSet.higherPriority(testSet.items[0], testSet.items[1]));
	}

	// Testing a normal sortDown function
	public void testDown() throws HeapFullException {
		testSet.add(new Node(true, 0, 0));
		testSet.add(new Node(true, 1, 1));
		((Node) testSet.items[0]).gCost = 10;
		((Node) testSet.items[0]).hCost = 10;
		((Node) testSet.items[1]).gCost = 1;
		((Node) testSet.items[1]).hCost = 1;
		assertFalse(testSet.higherPriority(testSet.items[0], testSet.items[1]));
		testSet.testSortDown(testSet.items[0]);
		assertTrue(testSet.higherPriority(testSet.items[0], testSet.items[1]));
	}

	public void testHeapFull() throws HeapFullException {
		try {
			testSet.add(new Node(true, 0, 0));
			testSet.add(new Node(true, 1, 1));
			testSet.add(new Node(true, 2, 2));
		} catch (HeapFullException HeapFullException) {
			System.out.println("Caught HeapFullException");
		}
	}
}
