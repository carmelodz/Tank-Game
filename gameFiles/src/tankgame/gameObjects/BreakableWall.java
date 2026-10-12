package tankgame.gameObjects;

public class BreakableWall extends GameObject {

    private static long uniqueWallId = 0L;
    private static final String WALL_ID_PREFIX = "wall-";
    private int healthPoint = 5;

    public BreakableWall(double x, double y, double angle) {
        super(getWallId(), x, y, 0);
    }

    private static String getWallId() {
        return WALL_ID_PREFIX + uniqueWallId++;
    }

    public int getHealthPoint(){return healthPoint;}
    public void decreaseHealthPoint() {healthPoint--;}

    @Override
    public double getXBound(){return getX() + 32.0; }

    @Override
    public double getYBound(){return getY() + 32.0; }

    @Override
    public double getSpeed(){return 0; }

    @Override
    public void move(GameWorld gameWorld){ }

    @Override
    public void checkBounds(){}

    @Override
    public boolean isAtBorder(){ return false;}
}
