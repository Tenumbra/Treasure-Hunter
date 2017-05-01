package org.csc301;

import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Grid extends Applet {

	private final int DEFAULT_WIDTH = 60; // default width of the world map -
											// gridX runs from 0 to 59
	private final int DEFAULT_HEIGHT = 15; // default height of the map - gridY
											// runs from 0 to 14
	private final int DEFAULT_PERCENT = 20; // this is the percentage of the map
											// occupied by islands
	protected int width, height; // user defined width and height, if one is not
									// using defaults
	protected int percent; // user defined percentage of islands on the map
	protected Node treasure; // points to the map node where the Redbeard
								// treasure is sunken
	protected Node boat; // points to the current location of our boat on the
							// map

	protected Node[][] map; // the map

	public Grid() {
		width = DEFAULT_WIDTH;
		height = DEFAULT_HEIGHT;
		percent = DEFAULT_PERCENT;
		map = new Node[height][width];
		buildMap();

	}

	public Grid(int width, int height, int percent) {
		this.height = height;
		this.width = width;
		if (percent <= 0 || percent >= 100)
			this.percent = DEFAULT_PERCENT;
		else
			this.percent = percent;
		map = new Node[height][width];
		buildMap();
	}

	private void buildMap() {
		// Your implementation goes here1
		// For each map position (i,j) you need to generate a Node with can be
		// navigable or it may belong to an island
		// You may use ideas from Lab3 here.
		// Don't forget to generate the location of the boat and of the
		// treasure; they must be on navigable waters, not on the land!

		Random random = new Random();
		int randNumber; // number from 1-100

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				randNumber = random.nextInt(100) + 1;

				if (randNumber <= percent)
					map[i][j] = new Node(false, j, i);
				else
					map[i][j] = new Node(true, j, i);
			}
		}

		boat = RandWalkable(random);
		treasure = RandWalkable(random);
	}

	private Node RandWalkable(Random random) { // Return a random walkable node
		int randWidth = random.nextInt(width);
		int randHeight = random.nextInt(height);

		while (!map[randHeight][randWidth].walkable) {
			randWidth = random.nextInt(width);
			randHeight = random.nextInt(height);
		}

		return map[randHeight][randWidth];
	}

	public String drawMap() {
		// provided for your convenience
		String result = "";
		String hline = "       ";
		String extraSpace;
		for (int i = 0; i < width / 10; i++)
			hline += "         " + (i + 1);
		result += hline + "\n";
		hline = "       ";
		for (int i = 0; i < width; i++)
			hline += (i % 10);
		result += hline + "\n";
		for (int i = 0; i < height; i++) {
			if (i < 10)
				extraSpace = "      ";
			else
				extraSpace = "     ";
			hline = extraSpace + i;
			for (int j = 0; j < width; j++) {
				if (i == boat.gridY && j == boat.gridX)
					hline += "B";
				else if (i == treasure.gridY && j == treasure.gridX)
					hline += "T";
				else if (map[i][j].inPath)
					hline += "*";
				else if (map[i][j].walkable)
					hline += ".";
				else
					hline += "+";
			}
			result += hline + i + "\n";
		}
		hline = "       ";
		for (int i = 0; i < width; i++)
			hline += (i % 10);
		result += hline + "\n";
		return result;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getPercent() {
		return percent;
	}

	public Node getBoat() {
		return boat;
	}

	private ArrayList<Node> getNeighbours(Node node) { // for convenience this
														// method will only
														// return walkable nodes
		ArrayList<Node> neighbours = new ArrayList<Node>();
		int x = node.gridX;
		int y = node.gridY;

		try {
			if (map[y - 1][x].walkable)
				neighbours.add(map[y - 1][x]);
		} catch (IndexOutOfBoundsException e) {
		} // It's fine to fail to add nodes that doesn't exist

		try {
			if (map[y + 1][x].walkable)
				neighbours.add(map[y + 1][x]);
		} catch (IndexOutOfBoundsException e) {
		}

		try {
			if (map[y][x + 1].walkable)
				neighbours.add(map[y][x + 1]);
		} catch (IndexOutOfBoundsException e) {
		}

		try {
			if (map[y][x - 1].walkable)
				neighbours.add(map[y][x - 1]);
		} catch (IndexOutOfBoundsException e) {
		}

		try {
			if (map[y - 1][x + 1].walkable)
				neighbours.add(map[y - 1][x + 1]);
		} catch (IndexOutOfBoundsException e) {
		}

		try {
			if (map[y + 1][x + 1].walkable)
				neighbours.add(map[y + 1][x + 1]);
		} catch (IndexOutOfBoundsException e) {
		}

		try {
			if (map[y - 1][x - 1].walkable)
				neighbours.add(map[y - 1][x - 1]);
		} catch (IndexOutOfBoundsException e) {
		}

		try {
			if (map[y + 1][x - 1].walkable)
				neighbours.add(map[y + 1][x - 1]);
		} catch (IndexOutOfBoundsException e) {
		}

		return neighbours;
		// each node has at most 8 neighbours
		// Lab3 may be useful here as well
	}

	private int getDistance(Node nodeA, Node nodeB) {
		// helper method. Provided for your convenience.
		int dstX = Math.abs(nodeA.gridX - nodeB.gridX);
		int dstY = Math.abs(nodeA.gridY - nodeB.gridY);
		if (dstX > dstY)
			return 14 * dstY + 10 * (dstX - dstY);
		return 14 * dstX + 10 * (dstY - dstX);
	}

	public int testGetDistance(Node nodeA, Node nodeB) {
		return getDistance(nodeA, nodeB);
	}

	public void findPath(Node startNode, Node targetNode) throws HeapFullException, HeapEmptyException {
		Heap<Node> openSet = new Heap<Node>(width * height); // this where we
																// make use of
																// our heaps
		// The rest of your implementation goes here.
		// This method implements A-star path search algorithm.
		// The pseudocode is provided in the appropriate web links.
		// Make sure to use the helper method getNeighbours
		startNode.hCost = getDistance(startNode, targetNode);
		Set<Node> closedSet = new HashSet<Node>();
		openSet.add(startNode);

		Node currentNode;
		int tentative_gCost;

		while (!openSet.isEmpty()) {
			currentNode = openSet.removeFirst();
			closedSet.add(currentNode);
			if (currentNode == targetNode)
				break;

			for (Node neighbour : getNeighbours(currentNode)) {

				if (closedSet.contains(neighbour))
					continue;

				tentative_gCost = currentNode.gCost + getDistance(currentNode, neighbour);

				if (openSet.isEmpty() || !openSet.contains(neighbour))
					openSet.add(neighbour);

				else if (tentative_gCost >= neighbour.gCost)
					continue;

				neighbour.parent = currentNode;
				neighbour.gCost = tentative_gCost;
				neighbour.hCost = getDistance(neighbour, targetNode);
				openSet.updateItem(neighbour);

			}

		}

	}

	public ArrayList<Node> retracePath(Node startNode, Node endNode) {
		Node currentNode = endNode;
		ArrayList<Node> path = new ArrayList<Node>();
		while (currentNode != startNode && currentNode != null) {
			currentNode.inPath = true;
			path.add(currentNode);
			currentNode = currentNode.parent;
		}
		return path;
	}

	public void move(String direction) {
		// Direction may be: N,S,W,E,NE,NW,SE,SW
		// move the boat 1 cell in the required direction
		int x = boat.gridX;
		int y = boat.gridY;

		if (direction.equals("N")) {
			try {
				if (map[y - 1][x].walkable)
					boat = map[y - 1][x];
			} catch (IndexOutOfBoundsException e) {
			} // It's fine to fail to set "boat" to a node that doesn't exist
		}

		else if (direction.equals("S")) {
			try {
				if (map[y + 1][x].walkable)
					boat = map[y + 1][x];
			} catch (IndexOutOfBoundsException e) {
			}
		}

		else if (direction.equals("W")) {
			try {
				if (map[y][x - 1].walkable)
					boat = map[y][x - 1];
			} catch (IndexOutOfBoundsException e) {
			}
		}

		else if (direction.equals("E")) {
			try {
				if (map[y][x + 1].walkable)
					boat = map[y][x + 1];
			} catch (IndexOutOfBoundsException e) {
			}
		}

		else if (direction.equals("NE")) {
			try {
				if (map[y - 1][x + 1].walkable)
					boat = map[y - 1][x + 1];
			} catch (IndexOutOfBoundsException e) {
			}
		}

		else if (direction.equals("NW")) {
			try {
				if (map[y - 1][x - 1].walkable)
					boat = map[y - 1][x - 1];
			} catch (IndexOutOfBoundsException e) {
			}
		}

		else if (direction.equals("SE")) {
			try {
				if (map[y + 1][x + 1].walkable)
					boat = map[y + 1][x + 1];
			} catch (IndexOutOfBoundsException e) {
			}
		}

		else if (direction.equals("SW")) {
			try {
				if (map[y + 1][x - 1].walkable)
					boat = map[y + 1][x - 1];
			} catch (IndexOutOfBoundsException e) {
			}
		}
	}

	public Node getTreasure(int range) {
		// range is the range of the sonar
		// if the distance of the treasure from the boat is less or equal that
		// the sonar range,
		// return the treasure node. Otherwise return null.

		if (getDistance(boat, treasure) <= range)
			return treasure;

		return null;
	}

	public void paint(Graphics g) {
		Image land = getImage("land.png");
		Image sea = getImage("sea.png");
		Image path = getImage("path.png");
		Image boatImage = getImage("boat.png");
		Image treasureImage = getImage("treasure.png");

		Graphics2D g2 = (Graphics2D) g;
		// this.setPreferredSize(new Dimension(width * 20, height * 20));
		this.setSize(width * 20, height * 20);

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (map[i][j] == boat)
					g2.drawImage(boatImage, j * 20, i * 20, 20, 20, this);
				else if (map[i][j] == treasure)
					g2.drawImage(treasureImage, j * 20, i * 20, 20, 20, this);
				else if (map[i][j].inPath)
					g2.drawImage(path, j * 20, i * 20, 20, 20, this);
				else if (map[i][j].walkable)
					g2.drawImage(sea, j * 20, i * 20, 20, 20, this);
				else
					g2.drawImage(land, j * 20, i * 20, 20, 20, this);
			}
		}

	}

	public Image getImage(String path) {
		Image tempImage = null;

		URL imageURL = Grid.class.getResource(path);
		tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);

		return tempImage;

	}

	// public static void main(String[] args) throws HeapFullException,
	// HeapEmptyException { // testing
	// Grid map = new Grid();
	// System.out.println(map.drawMap());
	//
	// map.findPath(map.boat, map.treasure);
	// System.out.println(map.drawMap());
	// }
}
