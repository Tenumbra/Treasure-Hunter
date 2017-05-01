package org.csc301;

import java.io.IOException;
import redbread.TreasureHunt;

// This class is not part of your evaluation. You may use it for testing if you want.
public class GameTest {
	/**
	 * @param args
	 * @throws HeapEmptyException
	 * @throws HeapFullException
	 * @throws IOException
	 */
	public static void main(String[] args) throws HeapFullException, HeapEmptyException, IOException {
		// TODO Auto-generated method stub
		TreasureHunt game = new TreasureHunt(15, 60, 20, 5, 210);

		game.play("C:\\Users\\0\\Documents\\GitHub\\assignment4-l-jenkins\\A4\\src\\main\\java\\redbread\\game.txt");
		System.out.println("\n" + game.getState());

		// System.out.println(game.getMap());
		// System.out.println(game.getState());
		// System.out.println(game.pathLength());
		// game.getIslands().findPath(game.getIslands().boat,
		// game.getIslands().treasure);
		// game.getIslands().retracePath(game.getIslands().boat,
		// game.getIslands().treasure);
		// new Frame(game.getIslands());
	}

}
