package tankgame.gameObjects;

public abstract class Tank extends GameObject { //Abstract class for Player Tanks to inherit/implement

    private double MOVEMENT_SPEED = 2.0;
    private int healthPoint = 20;

    public Tank(String id, double x, double y, double angle){
        super(id, x, y, angle);
    }

    public int getHealthPoint(){return healthPoint;}
    public void decreaseHealthPoint() {healthPoint--;}

    public void powerUp(){
        healthPoint = healthPoint + 10; //Increase tank health by 10 points
        MOVEMENT_SPEED = MOVEMENT_SPEED * 2; //Increase tank speed by 2
    }


    @Override
    public double getXBound(){return getX() + 55.0; }

    @Override
    public double getYBound(){return getY() + 55.0; }

    @Override
    public double getSpeed(){
        return MOVEMENT_SPEED;
    }

    @Override
    public void checkBounds(){ //Checks if player tank is at the border of the game screen
        if(this.getX() < GameWorld.TANK_X_LOWER_BOUND){
            this.setX(GameWorld.TANK_X_LOWER_BOUND);
        }

        if(this.getX() > GameWorld.TANK_X_UPPER_BOUND){
            this.setX(GameWorld.TANK_X_UPPER_BOUND);
        }

        if(this.getY() < GameWorld.TANK_Y_LOWER_BOUND){
            this.setY(GameWorld.TANK_Y_LOWER_BOUND);
        }

        if(this.getY() > GameWorld.TANK_Y_UPPER_BOUND){
            this.setY(GameWorld.TANK_Y_UPPER_BOUND);
        }
    }

    @Override
    public boolean isAtBorder(){ return false;}

    protected void shoot(GameWorld gameWorld){
        Bullet bullet = new Bullet(getBulletX(), getBulletY(), getAngle(), getId());
        gameWorld.addBullet(bullet);
    }

    private double getBulletX() {
        return getX() + 30.0 * (Math.cos(getAngle()) + 0.5);
    }
    private double getBulletY() {
        return getY() + 30.0 * (Math.sin(getAngle()) + 0.5);
    }
}
