
/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.*;


public class Ball extends CircularObjects {
	public static final int INIT_POS_X = 250;
	public static final int INIT_POS_Y = 170;
	public static final int INIT_VEL_X = -2;
	public static final int INIT_VEL_Y = -3;

	public Ball(int courtWidth, int courtHeight, int radius , Color color) {
		super(INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, INIT_POS_Y, radius, courtWidth, courtHeight, color);
	}

}