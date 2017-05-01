package org.csc301;

import javax.swing.JFrame;

public class Frame extends JFrame {
	public Frame(Grid grid) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(grid.width * 20, grid.height * 22);
		setResizable(false);
		setTitle("Map");

		init(grid);
	}

	public void init(Grid grid) {
		setLocationRelativeTo(null);
		add(grid);
		// pack();

		setVisible(true);
	}

}
