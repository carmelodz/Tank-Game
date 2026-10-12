package tankgame.gameObjects;

public abstract class GameObject { //Abstract class for all game objects to inherit/implement
    protected static final double TANK_TURN_SPEED = Math.toRadians(3.0);

    private final String id;
    private double x;
    private double y;
    private double angle;

    public GameObject(String id, double x, double y, double angle) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public String getId() {
        return id;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getAngle() {
        return angle;
    }

    public abstract double getXBound();
    public abstract double getYBound();

    public void setX(double newX) {
        this.x = newX;
    }
    public void setY(double newY){
        this.y = newY;
    }

    public abstract double getSpeed();

    public abstract void move(GameWorld gameWorld);
    public abstract void checkBounds();
    public abstract boolean isAtBorder();


    protected void moveForward() {
        x += getSpeed() * Math.cos(angle);
        y += getSpeed() * Math.sin(angle);
    }

    protected void moveBackward() {
        x -= getSpeed() * Math.cos(angle);
        y -= getSpeed() * Math.sin(angle);
    }

    protected void turnLeft() {
        angle -= TANK_TURN_SPEED;
    }

    protected void turnRight() {
        angle += TANK_TURN_SPEED;
    }

}
