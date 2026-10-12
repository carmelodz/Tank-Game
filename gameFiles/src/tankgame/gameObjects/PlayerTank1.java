package tankgame.gameObjects;

public class PlayerTank1 extends Tank{

    public PlayerTank1(String id, double x, double y, double angle){
        super(id, x, y, angle);
    }

    @Override
    public void move(GameWorld gameWorld){
        if(gameWorld.getIsUpPressed()) {
            moveForward();
        }
        if(gameWorld.getIsDownPressed()){
            moveBackward();
        }
        if(gameWorld.getIsLeftPressed()){
            turnLeft();
        }
        if(gameWorld.getIsRightPressed()){
            turnRight();
        }
        if(gameWorld.getIsShootPressed()){
            shoot(gameWorld);
        }

    }
}
