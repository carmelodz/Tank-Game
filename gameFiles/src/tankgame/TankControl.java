package tankgame;
import tankgame.gameObjects.GameWorld;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TankControl implements KeyListener{ //Keyboard controls for player tanks

    private final GameWorld gameWorld;

    public TankControl(GameWorld gameWorld){
        this.gameWorld = gameWorld;
    }

    @Override
    public void keyTyped(KeyEvent event){

    }

    @Override
    public void keyPressed(KeyEvent event){
        int keyCode = event.getKeyCode();

        if(keyCode == KeyEvent.VK_W){
            gameWorld.upPressed();
        }

        if(keyCode == KeyEvent.VK_S){
            gameWorld.downPressed();
        }

        if(keyCode == KeyEvent.VK_A){
            gameWorld.leftPressed();
        }

        if(keyCode == KeyEvent.VK_D){
            gameWorld.rightPressed();
        }

        if(keyCode == KeyEvent.VK_SPACE){
            gameWorld.shootPressed();
        }

        if(keyCode == KeyEvent.VK_ESCAPE){
            gameWorld.escapePressed();
        }

        if(keyCode == KeyEvent.VK_UP){
            gameWorld.upArrowPressed();
        }

        if(keyCode == KeyEvent.VK_DOWN){
            gameWorld.downArrowPressed();
        }

        if(keyCode == KeyEvent.VK_LEFT){
            gameWorld.leftArrowPressed();
        }

        if(keyCode == KeyEvent.VK_RIGHT){
            gameWorld.rightArrowPressed();
        }

        if(keyCode == KeyEvent.VK_ENTER){
            gameWorld.shoot2Pressed();
        }

    }

    @Override
    public void keyReleased(KeyEvent event){
        int keyCode = event.getKeyCode();

        if(keyCode == KeyEvent.VK_W){
            gameWorld.upReleased();
        }
        if(keyCode == KeyEvent.VK_S){
            gameWorld.downReleased();
        }

        if(keyCode == KeyEvent.VK_A) {
            gameWorld.leftReleased();
        }

        if(keyCode == KeyEvent.VK_D){
            gameWorld.rightReleased();
        }

        if(keyCode == KeyEvent.VK_SPACE){
            gameWorld.shootReleased();
        }

        if(keyCode == KeyEvent.VK_ESCAPE){
            gameWorld.escapeReleased();
        }

        if(keyCode == KeyEvent.VK_UP){
            gameWorld.upArrowReleased();
        }
        if(keyCode == KeyEvent.VK_DOWN){
            gameWorld.downArrowReleased();
        }

        if(keyCode == KeyEvent.VK_LEFT) {
            gameWorld.leftArrowReleased();
        }

        if(keyCode == KeyEvent.VK_RIGHT){
            gameWorld.rightArrowReleased();
        }

        if(keyCode == KeyEvent.VK_ENTER){
            gameWorld.shoot2Released();
        }

    }
}
