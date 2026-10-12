package tankgame.gameObjects;

public class Bullet extends GameObject {
    private static final String BULLET_ID_PREFIX = "bullet-";

    private static final double MOVEMENT_SPEED = 4.0;

    private static long uniqueId = 0L;
    private static String bulletID;

    public Bullet(double x, double y, double angle, String bulletID) {
        super(getUniqueId(), x, y, angle);
        Bullet.bulletID = bulletID;
    }

    public String getBulletID(){
        return bulletID;
    }

    @Override
    public double getXBound(){return getX() + 24.0; }

    @Override
    public double getYBound(){return getY() + 24.0; }

    @Override
    public double getSpeed(){
        return MOVEMENT_SPEED;
    }

    @Override
    public void move(GameWorld gameWorld){
        moveForward();
    }

    @Override
    public void checkBounds(){}

    @Override
    public boolean isAtBorder(){ //Checks if bullet is at the border of the game screen
        if(this.getX() < GameWorld.BULLET_X_LOWER_BOUND){
            return true;
        }

        if(this.getX() > GameWorld.BULLET_X_UPPER_BOUND){
            return true;
        }

        if(this.getY() < GameWorld.BULLET_Y_LOWER_BOUND){
            return true;
        }

        if(this.getY() > GameWorld.BULLET_Y_UPPER_BOUND){
            return true;
        }

        return false;
    }


    private static String getUniqueId() {
        return BULLET_ID_PREFIX + uniqueId++;
    }
}
