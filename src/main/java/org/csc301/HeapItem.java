package org.csc301;

public interface HeapItem extends Comparable<HeapItem> {

	public void setHeapIndex(int index);

	public int getHeapIndex();

	public int compareTo(HeapItem other);

}
