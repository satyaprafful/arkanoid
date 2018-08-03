
/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.*;


public class Paddle extends RectangularObjects {
	public static final int HEIGHT = 8;
	public static int WIDTH = 70;
	public static final int INIT_POS_X = 250;
	public static final int INIT_POS_Y = 310;
	public static final int INIT_VEL_X = 0;
	public static final int INIT_VEL_Y = 0;
	private Color color;

	
	public Paddle(int courtWidth, int courtHeight, Color color) {
		super(INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, INIT_POS_Y, WIDTH, HEIGHT, courtWidth, courtHeight);

		this.color = color;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.color);
		g.fillRect(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
	}
	//This function is for PowerUp Implementations 
	public void setPaddleLength(int length) {
		WIDTH = length; 
	}

}