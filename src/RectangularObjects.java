public abstract class RectangularObjects extends GameObj {
	public static final int WIDTH = 0;
	public static final int HEIGHT = 0;
	public static final int INIT_POS_X = 0;
	public static final int INIT_POS_Y = 0;
	public static final int INIT_VEL_X = 0;
	public static final int INIT_VEL_Y = 0;

	public RectangularObjects(int INIT_VEL_X, int INIT_VEL_Y, int INIT_POS_X, int INIT_POS_Y, int WIDTH, int HEIGHT,
			int courtWidth, int courtHeight) {
		super(INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, INIT_POS_Y, WIDTH, HEIGHT, courtWidth, courtHeight);

	}

	// These three functions need not be defined for now, since only the ball and PowerUps are
	// moving. These can however be defined in the extension of the game. #Modularity
	@Override
	int intersects(GameObj that) {
		return 0;
	}

	//This function need not be defined because the moving paddle's bounds have already been clipped to court size
	@Override
	void hitWall() {
	}

	@Override
	void hitObj(GameObj that, int collisionSide) {
	}

}