import java.awt.*;
import java.util.*;

public class PowerUps extends CircularObjects {
	public static final int INIT_VEL_Y = 3;
	public static int INIT_POS_X;
	public static int INIT_POS_Y;
	private static int courtWidth;
	private static int courtHeight;
	private static Ball redPowerUp = new Ball(courtWidth, courtHeight, 3, Color.RED);
	private static Ball bluePowerUp = new Ball(courtWidth, courtHeight, 3, Color.BLUE);
	private static Ball yellowPowerUp = new Ball(courtWidth, courtHeight, 3, Color.YELLOW);
	private static Ball greenPowerUp = new Ball(courtWidth, courtHeight, 3, Color.GREEN);

	public PowerUps(int vy, int px, int py, int radius, int courtWidth, int courtHeight, Color color) {
		super(0, vy, px, py, radius, courtWidth, courtHeight, color);
		this.courtWidth = courtWidth;
		this.courtHeight = courtHeight;
	}
	//Creating a Random PowerUp!
	public static Ball createPowerUp() {
		Random rand = new Random();
		int powerUpNo = rand.nextInt(3);
		System.out.println(powerUpNo);
		ArrayList<Ball> list = new ArrayList<Ball>();
		list.add(redPowerUp);
		list.add(bluePowerUp);
		list.add(yellowPowerUp);
		list.add(greenPowerUp);
		return list.get(powerUpNo);
	}
}
