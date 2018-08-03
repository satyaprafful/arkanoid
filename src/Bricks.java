import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

public class Bricks {
	private static int columns;
	private static int rows;
	private static final Color[] colors = { Color.BLACK, Color.BLUE, Color.DARK_GRAY, Color.orange, Color.GREEN,
			Color.RED };
	private static Brick[][] bricks;
	private static File file;
	private static ArrayList<Integer> values;
	private static int blocksCreated = 0;

	public Bricks(int courtWidth, int courtHeight, File file) {
		this.file = file;
		getLevelData();
		bricks = new Brick[columns][rows];
		int newXPos = 20;
		int newYPos = 0;
		int i = 2;
		for (int x = 0; x < columns; x++) {
			for (int y = 0; y < rows; y++) {
				bricks[x][y] = new Brick(newXPos, newYPos, courtWidth, courtHeight, colors[y%6]);
				newYPos += 21;
			}
			newXPos += 51;
			newYPos = 0;
		}
		setLevel();
	}

	public Brick[][] getBricksArray() {
		return bricks;
	}

	// Reads Level Data from the text file, and stores the Integer-binary values in
	// an ArrayList
	public static void getLevelData() {
		try {
			Scanner scanner = new Scanner(file);
			int value;
			values = new ArrayList<>();
			while ((scanner.hasNextInt())) {
				value = scanner.nextInt();
				values.add(value);
			}
			scanner.close();
			columns = values.get(0);
			rows = values.get(1);

		} catch (FileNotFoundException ex) {
			System.out.print("ERROR : Level Data File Not Found!");
		}

	}

	// Iterates through the created 2D Brick Array in the Constructor, and takes the
	// values from the
	// ArrayList and sets the DNEs accordingly. The draw function takes care of
	// drawing only those that exist
	public static void setLevel() {
		int i = 2;
		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < columns; x++) {
				if (values.get(i) == 0) {
					bricks[x][y].setDNE();
				} else if (values.get(i) == 1) {
					blocksCreated++;
				}
				i++;
			}
		}
	}

	public static int getRows() {
		return rows;
	}

	public static int getColumns() {
		return columns;
	}

	public static int getBlocksCreated() {
		return blocksCreated;
	}

	// Only draws those blocks that "Exist"
	public void draw(Graphics g) {
		for (int x = 0; x < getColumns(); x++) {
			for (int y = 0; y < getRows(); y++) {
				if (bricks[x][y].exists()) {
					bricks[x][y].draw(g);
				}
			}
		}
	}
}
