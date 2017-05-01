package org.csc301;

import java.io.IOException;

import junit.framework.TestCase;
import redbread.TreasureHunt;

public class treasureHuntTest extends TestCase {
	
	TreasureHunt game = new TreasureHunt(15, 60, 20, 5, 1000);
	int previousX = game.getIslands().boat.gridX;
	int previousY = game.getIslands().boat.gridY;
	
	String PATH_NAME = "C:\\Users\\0\\Documents\\GitHub\\assignment4-l-jenkins"
			+ "\\A4\\src\\main\\java\\redbread\\game.txt";
	
	// String PATH_NAME = "C:\\Users\\leet-\\Documents\\301\\a4"
	// + "\\assignment4-l-jenkins\\A4\\src\\game.txt";
	
	public void testPlay() throws HeapFullException, HeapEmptyException, IOException {
		game.play(PATH_NAME);
		assertEquals(game.getState(), "OVER");
	}

	// Passes if it goes north
	public void testProcessCommandN() throws HeapFullException, HeapEmptyException {
		Node temp = game.getIslands().getBoat();
		game.processCommand("GO N");

		if (previousY - 1 >= 0 && game.getIslands().map[previousY - 1][previousX].walkable)
			assertFalse(temp.equals(game.getIslands().getBoat()));
		else
			assertTrue(temp.equals(game.getIslands().getBoat()));
	}

	public void testProcessCommandSONAR() throws HeapFullException, HeapEmptyException {
		Node temp = game.getIslands().getBoat();
		game.processCommand("SONAR");
		assertTrue(temp.equals(game.getIslands().getBoat()));
	}
	
    public void testProcessCommandSNR() throws HeapFullException, 
    HeapEmptyException {
        Node temp = game.getIslands().getBoat();
        game.processCommand("SONAR");
        assertTrue(temp.equals(game.getIslands().getBoat()));
    }

	public void testPathLengthP() throws HeapFullException, HeapEmptyException, IOException {
		game.play(PATH_NAME);
		assertTrue(game.pathLength() > 0);
	}

	public void testPathLengthN() throws HeapFullException, HeapEmptyException, IOException {
		TreasureHunt badRun = new TreasureHunt(15, 60, 20, 5, 0);
		badRun.play(PATH_NAME);
		assertTrue(badRun.pathLength() == 0);
	}

	public void testGetMap() throws HeapFullException, HeapEmptyException {
		String temp = game.getMap();
		game.processCommand("GO N");

		if (previousY - 1 >= 0 && game.getIslands().map[previousY - 1][previousX].walkable)
			assertFalse(temp.equals(game.getMap()));
		else
			assertTrue(temp.equals(game.getMap()));
	}

}