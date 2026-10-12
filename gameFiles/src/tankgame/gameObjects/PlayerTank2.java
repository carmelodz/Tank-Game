package tankgame.gameObjects;

public class PlayerTank2 extends Tank {
    public PlayerTank2(String id, double x, double y, double angle) {
        super(id, x, y, angle);
    }

    @Override
    public void move(GameWorld gameWorld) {
        if(gameWorld.getIsUpArrowPressed()) {
            moveForward();
        }
        if(gameWorld.getIsDownArrowPressed()){
            moveBackward();
        }
        if(gameWorld.getIsLeftArrowPressed()){
            turnLeft();
        }
        if(gameWorld.getIsRightArrowPressed()){
            turnRight();
        }
        if(gameWorld.getIsShoot2Pressed()){
            shoot(gameWorld);
        }
    }
}
