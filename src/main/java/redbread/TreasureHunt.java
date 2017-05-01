package redbread;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.csc301.Frame;
import org.csc301.Grid;
import org.csc301.HeapEmptyException;
import org.csc301.HeapFullException;
import org.csc301.Node;

public class TreasureHunt {

	private final int DEFAULT_SONARS = 3; // default number of available sonars
	private final int DEFAULT_RANGE = 200; // default range of a sonar
	private Grid islands; // the world where the action happens!
	protected int height, width, landPercent;
	protected int sonars, range; // user defined number of sonars and range
	private String state; // state of the game (STARTED, OVER)
	public ArrayList<Node> path; // the path to the treasure!

	public TreasureHunt() {
		// The default constructor
		this.sonars = DEFAULT_SONARS;
		this.range = DEFAULT_RANGE;
		islands = new Grid();
		state = "STARTED";
	}

	public TreasureHunt(int height, int width, int landPercent, int sonars, int range) {
		// The constructor that uses parameters
		this.sonars = sonars;
		this.range = range;
		islands = new Grid(width, height, landPercent);
		state = "STARTED";
	}

	public void processCommand(String command) throws HeapFullException, HeapEmptyException {
		// The allowed commands are:
		// SONAR to drop the sonar in hope to detect treasure
		// GO direction to move the boat in some direction
		// For example, GO NW means move the boat one cell up left (if the cell
		// is navigable; if not simply ignore the command)
		System.out.println(command);

		if (command.substring(0, 2).equals("GO")) {
			islands.move(command.substring(command.length() - 2, command.length()).replaceAll("\\s+", ""));
			System.out.println(getMap());
		}

		else if (command.equals("SONAR")) {
			Node treasure = islands.getTreasure(range);

			if (treasure != null) {
				islands.findPath(islands.getBoat(), treasure);
				path = islands.retracePath(islands.getBoat(), treasure);
				state = "OVER";
				System.out.println(getMap());
				System.out.println("YOU WIN!\nPath Length: " + pathLength());

			} else {
				sonars--;
				if (sonars == 0) {
					state = "OVER";
					System.out.println(getMap());
					System.out.println("YOU LOSE!\nPath Length: " + pathLength());
				}

				else
					System.out.println(getMap());
			}
		}

	}

	public int pathLength() {
		if (path == null)
			return 0;
		else
			return path.size();
	}

	public String getMap() {
		return islands.drawMap();
	}

	public void play(String pathName) throws HeapFullException, HeapEmptyException, IOException {
		// Read a batch of commands from a text file and process them.
		BufferedReader br = null;
		FileReader fr = null;

		try {
			fr = new FileReader(pathName);
			br = new BufferedReader(fr);

			String sCurrentLine;

			br = new BufferedReader(new FileReader(pathName));

			while ((sCurrentLine = br.readLine()) != null && !state.equals("OVER")) {
				processCommand(sCurrentLine);
			}

			new Frame(islands);

		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	public String getState() {
		return state;
	}

	public Grid getIslands() {
		return islands;
	}

}
