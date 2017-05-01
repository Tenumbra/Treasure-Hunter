package org.csc301;

public class Heap<T extends HeapItem> {

	// Note the T is a parameter representing a type that extends the HeapItem
	// interface
	// This a new way to use inheritance!

	protected T[] items; // Array that is used to store heap items. items[0] is
							// the highest priority element.
	protected int maxHeapSize; // The capacity of the heap
	protected int currentItemCount; // How many elements we have currently on
									// the heap

	public Heap(int maxHeapSize) {
		this.maxHeapSize = maxHeapSize;
		items = (T[]) new HeapItem[maxHeapSize];
		currentItemCount = 0; // heap is empty!
	}

	public boolean isEmpty() {
		return currentItemCount == 0;
	}

	public boolean isFull() {
		return currentItemCount == maxHeapSize;
	}

	public void add(T item) throws HeapFullException
	// Adds item T to its correct position on the heap
	{
		if (isFull())
			throw new HeapFullException();
		else {
			item.setHeapIndex(currentItemCount);
			items[currentItemCount] = item; // the element is added to the
											// bottom
			currentItemCount++;
		}
	}

	public boolean contains(T item)
	// Returns true if item is on the heap
	// Otherwise returns false
	{
		return items[item.getHeapIndex()].equals(item);
	}

	public int count() {
		return currentItemCount;
	}

	public void updateItem(T item) {
		sortUp(item);
	}

	public T removeFirst() throws HeapEmptyException
	// Removes and returns the element sitting on top of the heap
	{
		if (isEmpty())
			throw new HeapEmptyException();
		else {
			T firstItem = items[0]; // element of top of the heap is stored in
									// firstItem variable
			currentItemCount--;

			if (currentItemCount == 0) {
				items[0] = null;
				return firstItem;
			}

			switchIndex(0, currentItemCount);

			items[currentItemCount] = null;
			sortDown(items[0]); // move the element to its legitimate position.
								// Please check the diagram on the handout.
			return firstItem;
		}
	}

	private void sortDown(T item) {
		// Implement this method according to the diagram on the handout.
		// Also: the indices of children and parent elements satisfy some
		// relationships.
		// The formulas are on the handout.

		int index = item.getHeapIndex();

		while (itemExistAtIndex((index * 2) + 1) || itemExistAtIndex((index * 2) + 2)) {
			if (itemExistAtIndex((index * 2) + 1) && itemExistAtIndex((index * 2) + 2)) {
				if (higherPriority(items[(index * 2) + 1], (items[(index * 2) + 2]))
						&& higherPriority(items[(index * 2) + 1], items[index])) {
					switchIndex((index * 2) + 1, index);
					index = (index * 2) + 1;
				} else if (higherPriority(items[(index * 2) + 2], items[index])) {
					switchIndex((index * 2) + 2, index);
					index = (index * 2) + 2;
				} else
					break;
			}

			else if (itemExistAtIndex((index * 2) + 1)) {
				if (higherPriority(items[(index * 2) + 1], items[index])) {
					switchIndex((index * 2) + 1, index);
					index = (index * 2) + 1;
				} else
					break;
			}

			else {
				if (higherPriority(items[(index * 2) + 2], items[index])) {
					switchIndex((index * 2) + 2, index);
					index = (index * 2) + 2;
				} else
					break;
			}
		}
	}
	
	public void testSortDown(T item) {
		sortDown(item);
	}

	private void sortUp(T item) {
		// Implement this method according to the diagram on the handout.
		// Also: the indices of children and parent elements satisfy some
		// relationships.
		// The formulas are on the handout.

		int index = item.getHeapIndex();

		while (higherPriority(items[index], items[(index - 1) / 2])) { // While item has higher priority than its parent
			switchIndex((index - 1) / 2, index);
			index = (index - 1) / 2;
		}

	}

	// You may implement additional helper methods if desired. Make sure to make
	// them private!

	public boolean higherPriority(T item, T other) { // Return true if item has higher priority than other, return false otherwise
		return item.compareTo(other) > 0;
	}

	private void switchIndex(int index1, int index2) { // Switch the indices of objects that corresponds to index1 and index2
		T temp = items[index1];
		items[index1] = items[index2];
		items[index2] = temp;

		items[index1].setHeapIndex(index1);
		items[index2].setHeapIndex(index2);
	}

	private boolean itemExistAtIndex(int index) { // Return true if the heap has an non-null object at index, return false otherwise
		try {
			items[index].hashCode();
			return true;
		} catch (IndexOutOfBoundsException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
	}

//	public static void main(String[] args) throws HeapFullException, HeapEmptyException { // testing
//		TreasureHunt game = new TreasureHunt();
//		System.out.println(game.getMap());
//		Heap<HeapItem> openSet = new Heap<HeapItem>(60 * 15);
//		openSet.add(new Node(true, 0, 0));
//		openSet.add(new Node(true, 0, 1));
//
//		((Node) openSet.items[0]).gCost = 5;
//		((Node) openSet.items[1]).gCost = 10;
//		System.out.println(openSet.higherPriority(openSet.items[0], openSet.items[1]));
//		System.out.println(openSet.higherPriority(openSet.items[1], openSet.items[0]));
//
//		System.out.println(openSet.itemExistAtIndex(0));
//		System.out.println(openSet.itemExistAtIndex(1));
//		System.out.println(openSet.itemExistAtIndex(2));
//		System.out.println(openSet.itemExistAtIndex(999999999));
//
//		System.out.println(openSet.items[0] + " " + openSet.items[0].getHeapIndex());
//		System.out.println(openSet.items[1] + " " + openSet.items[1].getHeapIndex());
//		openSet.switchIndex(0, 1);
//		System.out.println(openSet.items[0] + " " + openSet.items[0].getHeapIndex());
//		System.out.println(openSet.items[1] + " " + openSet.items[1].getHeapIndex());
//	}
}
