package org.csc301;

import junit.framework.TestCase;

public class nodeTest extends TestCase {

	// Tests a positive comparison node1 > node2
	public void testCompareToP() {
		Node node1 = new Node(true, 0, 0);
		Node node2 = new Node(true, 0, 0);
		node1.hCost = 5;
		node1.gCost = 5;
		node2.hCost = 10;
		node2.gCost = 10;
		assertEquals(10, node1.compareTo(node2));
	}

	// Tests a negative comparison node1 < node2
	public void testCompareToN() {
		Node node1 = new Node(true, 0, 0);
		Node node2 = new Node(true, 0, 0);
		node1.hCost = 10;
		node1.gCost = 10;
		node2.hCost = 5;
		node2.gCost = 5;
		assertEquals(-10, node1.compareTo(node2));
	}

	// Tests two equal nodes
	public void testEqualsT() {
		Node node1 = new Node(true, 0, 0);
		Node node2 = new Node(true, 0, 0);
		assertTrue(node1.equals(node2));
	}

	// Tests two nonequal nodes
	public void testEqualsF() {
		Node node1 = new Node(true, 0, 0);
		Node node2 = new Node(true, 1, 1);
		assertFalse(node1.equals(node2));
	}
}
