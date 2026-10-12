package tankgame.gameObjects;

import tankgame.gameScreen.RunGameView;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GameWorld { //GameWorld class handles adding and removing game objects, such as tanks and bullets
    public static final double TANK_X_LOWER_BOUND = 30.0;
    public static final double TANK_X_UPPER_BOUND = RunGameView.SCREEN_DIMENSIONS.width - 100.0;
    public static final double TANK_Y_LOWER_BOUND = 30.0;
    public static final double TANK_Y_UPPER_BOUND = RunGameView.SCREEN_DIMENSIONS.height - 120.0;

    public static final double BULLET_X_LOWER_BOUND = -10.0;
    public static final double BULLET_X_UPPER_BOUND = RunGameView.SCREEN_DIMENSIONS.width;
    public static final double BULLET_Y_LOWER_BOUND = -10.0;
    public static final double BULLET_Y_UPPER_BOUND = RunGameView.SCREEN_DIMENSIONS.height;

    public static final String PLAYER_TANK_ID = "player-tank";
    public static final String PLAYER_TANK2_ID = "player-tank2";
    public static final String POWER_UP_ID = "powerUp";

    private boolean isUpPressed;
    private boolean isDownPressed;
    private boolean isLeftPressed;
    private boolean isRightPressed;
    private boolean isShootPressed;
    private boolean isEscapePressed;

    private boolean isUpArrowPressed;
    private boolean isDownArrowPressed;
    private boolean isLeftArrowPressed;
    private boolean isRightArrowPressed;
    private boolean isShoot2Pressed;


    private final List<GameObject> objects = new ArrayList<>();
    private List<GameObject> newBullet = new ArrayList<>();

    public void addObject(GameObject tank){
        objects.add(tank);
    }
    public void addBullet(GameObject bullet) {
        newBullet.add(bullet);}

    public void rmObject(GameObject gameObject) {
        objects.remove(gameObject);
    }

    public void reset() {
        objects.removeAll(objects);
    }

    public List<GameObject> getObjects(){
        return objects;
    }
    public GameObject getObject(String id) {
        for(GameObject gameObject : objects){
            if(gameObject.getId().equals(id)){
                return gameObject;
            }
        }
        return null;
    }
    public List<GameObject> getNewBullet() {return newBullet;}

    //Player 1 Tank Control
    public void upPressed(){
        isUpPressed = true;
    }
    public void upReleased(){
        isUpPressed = false;
    }

    public void downPressed(){ isDownPressed = true; }
    public void downReleased(){ isDownPressed = false;}

    public void leftPressed(){
        isLeftPressed = true;
    }
    public void leftReleased(){ isLeftPressed = false; }

    public void rightPressed(){ isRightPressed = true; }
    public void rightReleased(){
        isRightPressed = false;
    }

    public void shootPressed() {isShootPressed = true;}
    public void shootReleased() {isShootPressed = false;}

    public void escapePressed() {isEscapePressed = true;}
    public void escapeReleased() {isEscapePressed = false;}

    //Player 2 Tank Control
    public void upArrowPressed(){
        isUpArrowPressed = true;
    }
    public void upArrowReleased(){
        isUpArrowPressed = false;
    }

    public void downArrowPressed(){ isDownArrowPressed = true; }
    public void downArrowReleased(){ isDownArrowPressed = false;}

    public void leftArrowPressed(){
        isLeftArrowPressed = true;
    }
    public void leftArrowReleased(){ isLeftArrowPressed = false; }

    public void rightArrowPressed(){ isRightArrowPressed = true; }
    public void rightArrowReleased(){
        isRightArrowPressed = false;
    }

    public void shoot2Pressed() {isShoot2Pressed = true;}
    public void shoot2Released() {isShoot2Pressed = false;}

    public boolean getIsUpArrowPressed(){
        return isUpArrowPressed;
    }

    public boolean getIsDownArrowPressed(){
        return isDownArrowPressed;
    }

    public boolean getIsLeftArrowPressed(){
        return isLeftArrowPressed;
    }


    public boolean getIsRightArrowPressed(){
        return isRightArrowPressed;
    }


    public boolean getIsShoot2Pressed() {
        if (isShoot2Pressed) {
            isShoot2Pressed = false;
            return true;
        } else {
            return false;
        }
    }



    public boolean getIsUpPressed(){
        return isUpPressed;
    }

    public boolean getIsDownPressed(){
        return isDownPressed;
    }

    public boolean getIsLeftPressed(){
        return isLeftPressed;
    }

    public boolean getIsRightPressed(){
        return isRightPressed;
    }

    public boolean getIsShootPressed() {
        if (isShootPressed) {
            isShootPressed = false;
            return true;
        } else {
            return false;
        }
    }

    public boolean getIsEscapePressed() {
        if (isEscapePressed) {
            isEscapePressed = false;
            return true;
        } else {
            return false;
        }
    }

    public void playSound(String soundFileName){
        try{
            URL url = this.getClass().getClassLoader().getResource(soundFileName);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.setFramePosition(0);
            clip.start();
        }catch(UnsupportedAudioFileException | IOException | LineUnavailableException e){
            e.printStackTrace();
        }
    }

}
