
/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Brick extends RectangularObjects {
	public static final int WIDTH = 50;
	public static final int HEIGHT = 20;
	public static int INIT_POS_X = 0;
	public static int INIT_POS_Y = 0;
	public static final int INIT_VEL_X = 0;
	public static final int INIT_VEL_Y = 0;
	private Color color;
	private boolean containsPowerUp;
	private boolean exists = true;


	public Brick(int pX, int pY, int courtWidth, int courtHeight, Color color) {
		super(INIT_VEL_X, INIT_VEL_Y, pX, pY, WIDTH, HEIGHT, courtWidth, courtHeight);
		INIT_POS_X = pX;
		INIT_POS_Y = pY;
		this.color = color;

	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.color);
		g.fillRect(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
	}

	// Getter for exists
	public boolean exists() {
		return exists;
	}

	// Does Not Exist setter
	public void setDNE() {
		exists = false;
	}

	// getter for contains Power Up
	public boolean containsPowerUp() {
		return containsPowerUp;
	}
	// setter for contains Power Up
	public void setContainsPowerUp() {
		containsPowerUp = true; 
	}

}