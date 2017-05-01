package org.csc301;

import junit.framework.TestCase;
import redbread.TreasureHunt;

public class gridTest extends TestCase {

	// Grid Constructor
	Grid grid = new Grid();
	int previousX = grid.boat.gridX;
	int previousY = grid.boat.gridY;
	TreasureHunt game = new TreasureHunt(15, 60, 20, 5, 200);

	public void testFindPath() throws HeapFullException, HeapEmptyException {
		grid.findPath(grid.boat, grid.treasure);
	}

	public void testRetracePath() throws HeapFullException, HeapEmptyException {
		grid.findPath(grid.boat, grid.treasure);
		System.out.println(grid.retracePath(grid.boat, grid.treasure));
	}

	// Test equal node when in range
	public void testGetTreasureR() {
		assertEquals(grid.getTreasure(1000), grid.treasure);
	}

	// Test returns null when treasure out of range
	public void testGetTreasureN() {
		assertNull(grid.getTreasure(grid.testGetDistance(grid.boat, grid.treasure) - 1));
	}

	// Tests moving north
	public void testMoveN() {
		grid.move("N");
		if (previousY - 1 >= 0 && grid.map[previousY - 1][previousX].walkable) {
			assertEquals(previousX, grid.boat.gridX);
			assertEquals(previousY - 1, grid.boat.gridY);
			previousY--;
		} else {
			assertEquals(previousX, grid.boat.gridX);
			assertEquals(previousY, grid.boat.gridY);
		}
	}

	// Tests moving south
	public void testMoveS() {
		grid.move("S");
		if (previousY + 1 < grid.height && grid.map[previousY + 1][previousX].walkable) {
			assertEquals(previousX, grid.boat.gridX);
			assertEquals(previousY + 1, grid.boat.gridY);
			previousY++;
		} else {
			assertEquals(previousX, grid.boat.gridX);
			assertEquals(previousY, grid.boat.gridY);
		}
	}

}
