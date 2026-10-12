package tankgame;

import tankgame.gameObjects.*;
import tankgame.gameObjects.GameObject;
import tankgame.gameScreen.UI_Menu_View;
import tankgame.gameScreen.RunGameView;
import tankgame.gameScreen.Start_End_Menu_View;

import javax.sound.sampled.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameDriver { //Main class driver for game
    private final UI_Menu_View UIMenuView;
    private final RunGameView runGameView;
    private final GameWorld gameWorld;
    private final TankControl tankControl;
    private final GameActionListener gameActionListener;
    private int playerTankCount = 0;
    private Tank playerTank;
    private Tank playerTank2;
    private Clip clip;


    public GameDriver() {
        gameWorld = new GameWorld();
        tankControl = new TankControl(gameWorld);
        gameActionListener = new GameActionListener();
        UIMenuView = new UI_Menu_View(tankControl, gameActionListener);
        runGameView = UIMenuView.getRunGameView();
    }

    public void start() {
        UIMenuView.setScreen(UI_Menu_View.Screen.START_MENU_SCREEN);
    }

    private void runGame() { //Initialize and draw all game objects to their initial game location
        playerTank = new PlayerTank1(
                        GameWorld.PLAYER_TANK_ID,
                        RunGameView.PLAYER_TANK_INITIAL_X,
                        RunGameView.PLAYER_TANK_INITIAL_Y,
                        RunGameView.PLAYER_TANK_INITIAL_ANGLE);
        playerTankCount++;

        playerTank2 = new PlayerTank2(
                GameWorld.PLAYER_TANK2_ID,
                RunGameView.PLAYER_TANK2_INITIAL_X,
                RunGameView.PLAYER_TANK2_INITIAL_Y,
                RunGameView.PLAYER_TANK2_INITIAL_ANGLE);
        playerTankCount++;


        PowerUp powerUp = new PowerUp(
                        GameWorld.POWER_UP_ID,
                        RunGameView.POWER_UP_INITIAL_X,
                        RunGameView.POWER_UP_INITIAL_Y,
                        RunGameView.POWER_UP_INITIAL_ANGLE
                        );

        gameWorld.addObject(playerTank);
        gameWorld.addObject(playerTank2);
        gameWorld.addObject(powerUp);

        runGameView.addDrawableObject(
                GameWorld.PLAYER_TANK_ID,
                RunGameView.PLAYER_TANK_IMAGE_FILE,
                playerTank.getX(),
                playerTank.getY(),
                playerTank.getAngle()
        );

        runGameView.addDrawableObject(
                GameWorld.PLAYER_TANK2_ID,
                RunGameView.PLAYER_TANK2_IMAGE_FILE,
                playerTank2.getX(),
                playerTank2.getY(),
                playerTank2.getAngle()
        );

        runGameView.addDrawableObject(
                 GameWorld.POWER_UP_ID,
                 RunGameView.POWER_UP_IMAGE_FILE,
                 powerUp.getX(),
                 powerUp.getY(),
                 powerUp.getAngle()
        );

        List<WallMap> wallMapList = WallMap.readWalls(); //Read WallMap and create game walls through Object
        for(WallMap wallInfo: wallMapList){
            BreakableWall newBreakableWall = new BreakableWall(wallInfo.getX(), wallInfo.getY(),0);
//            UnbreakableWall newUbBreakableWall = new UnbreakableWall(wallInfo.getX(), wallInfo.getY(),0);
            gameWorld.addObject(newBreakableWall);
//            gameWorld.addObject(newUbBreakableWall);
            runGameView.addDrawableObject(newBreakableWall.getId(), wallInfo.getImageFile(), newBreakableWall.getX(), newBreakableWall.getY(), 0);
//            runGameView.addDrawableObject(newUbBreakableWall.getId(), wallInfo.getImageFile(), newBreakableWall.getX(), newBreakableWall.getY(), 0);
        }

        Runnable gameRunner = () -> {
            while (update()) {
                runGameView.repaint();
                try {
                    Thread.sleep(8L);
                } catch (InterruptedException exception) {
                    throw new RuntimeException(exception);
                }
            }
        };
        new Thread(gameRunner).start();
    }

    private boolean update() { // Update/re-draw all game objects to their new location
        for(GameObject gameObject : gameWorld.getObjects()){
            gameObject.move(gameWorld);
        }

        for(GameObject gameObject : gameWorld.getObjects() ){
            gameObject.checkBounds();
        }


        List<GameObject> newBulletObjects = gameWorld.getNewBullet();
        for(GameObject newBulletGameObject : newBulletObjects){
            runGameView.addDrawableObject(newBulletGameObject.getId(), RunGameView.BULLET_IMAGE_FILE, newBulletGameObject.getX(), newBulletGameObject.getY(), newBulletGameObject.getAngle());
        }

        for (GameObject gameObject : newBulletObjects) {
            gameWorld.addObject(gameObject);
        }
        newBulletObjects.removeAll(newBulletObjects);


        List<GameObject> removeBullets = new ArrayList<>();
        for(GameObject gameObject : gameWorld.getObjects()){
            if (gameObject.isAtBorder()) {
                removeBullets.add(gameObject);
            }
        }

        for(GameObject gameObject : removeBullets){
            gameWorld.rmObject(gameObject);
            runGameView.removeDrawableObject(gameObject.getId());
        }

        List<GameObject> allRemovableObjects = new ArrayList<>();
        List<GameObject> allObjects = gameWorld.getObjects();
        int size = allObjects.size();
        for(int i = 0; i < size - 1 ; i++){
            for(int j = i + 1; j < size; j++){
                if(objectsOverlapping(allObjects.get(i), allObjects.get(j))){
                    allRemovableObjects.addAll(CollisionDetection(allObjects.get(i), allObjects.get(j)));
                }
            }
        }

        for(GameObject gameObject : allRemovableObjects){
            gameWorld.rmObject(gameObject);
            runGameView.removeDrawableObject(gameObject.getId());
        }

        if(playerTankCount == 1) {
            if(playerTankCount == 1) {
                gameWorld.playSound("WinSound.wav");
            }
            resetGameScreen();
            return false;
        }

        allRemovableObjects.removeAll(allRemovableObjects);


        for(GameObject gameObject : gameWorld.getObjects()){
            runGameView.setDrawableObjectLocationAndAngle(gameObject.getId(), gameObject.getX(), gameObject.getY(), gameObject.getAngle());
        }

        if(gameWorld.getIsEscapePressed()){
            resetGameScreen();
            return false;
        }

        runGameView.showPlayerTank1HP(playerTank);
        runGameView.showPlayerTank2HP(playerTank2);
        return true;
    }

    //Checks if game objects are overlapping, such as player tanks, walls, and bullets
    private boolean objectsOverlapping(GameObject gameObject1, GameObject gameObject2) {
        return gameObject1.getX() < gameObject2.getXBound()
                && gameObject1.getXBound() > gameObject2.getX()
                && gameObject1.getY() < gameObject2.getYBound()
                && gameObject1.getYBound() > gameObject2.getY();
    }

    //Collision Detection Helper Functions
    private List<GameObject> CollisionDetection(GameObject gameObject1, GameObject gameObject2){
        List<GameObject> allRemovableObjects = new ArrayList<>();
        if (gameObject1 instanceof Tank && gameObject2 instanceof Tank) {
            TankvsTank(gameObject1, gameObject2);
        } else if (gameObject1 instanceof Bullet && gameObject2 instanceof Bullet) {
            allRemovableObjects.addAll(BulletvsBullet(gameObject1, gameObject2));
        } else if (gameObject1 instanceof Tank && gameObject2 instanceof Bullet) {
            allRemovableObjects.addAll(TankvsBullet(gameObject1, gameObject2));
        } else if (gameObject1 instanceof Bullet && gameObject2 instanceof Tank) {
            allRemovableObjects.addAll(TankvsBullet(gameObject2, gameObject1));
        } else if (gameObject1 instanceof Tank && gameObject2 instanceof BreakableWall){
            TankvsWall(gameObject1, gameObject2);
        } else if (gameObject1 instanceof BreakableWall && gameObject2 instanceof Tank){
            TankvsWall(gameObject2, gameObject1);
        } else if (gameObject1 instanceof Bullet && gameObject2 instanceof BreakableWall){
            allRemovableObjects.addAll(BulletvsWall(gameObject1, gameObject2));
        } else if (gameObject1 instanceof BreakableWall && gameObject2 instanceof Bullet) {
            allRemovableObjects.addAll(BulletvsWall(gameObject2, gameObject1));
        } else if (gameObject1 instanceof Tank && gameObject2 instanceof PowerUp) {
            allRemovableObjects.addAll(TankvsPowerUp(gameObject1, gameObject2));
        }else if (gameObject1 instanceof PowerUp && gameObject2 instanceof Tank) {
            allRemovableObjects.addAll(TankvsPowerUp(gameObject2, gameObject1));
        }
        return allRemovableObjects;
    }

    //Collision detection for player tanks
    private void TankvsTank(GameObject gameObject1, GameObject gameObject2){
        double distanceA = gameObject1.getXBound() - gameObject2.getX();
        double distanceB = gameObject2.getXBound() - gameObject1.getX();
        double distanceC = gameObject1.getYBound() - gameObject2.getY();
        double distanceD = gameObject2.getYBound() - gameObject1.getY();
        double[] distance = {distanceA,distanceB, distanceC, distanceD};
        Arrays.sort(distance);
        double minDistance = distance[0];

        //Checks the min distance of the hit-box border on each player tank
        if(minDistance == distanceA){
            gameObject1.setX (gameObject1.getX() - minDistance/2);
            gameObject2.setX (gameObject2.getX() + minDistance/2);
        }else if(distance[0] == distanceB){
            gameObject1.setX (gameObject1.getX() + minDistance/2);
            gameObject2.setX (gameObject2.getX() - minDistance/2);
        }else if(distance[0] == distanceC){
            gameObject1.setY (gameObject1.getY() - minDistance/2);
            gameObject2.setY (gameObject2.getY() + minDistance/2);
        }else {
            gameObject1.setY (gameObject1.getY() + minDistance/2);
            gameObject2.setY (gameObject2.getY() - minDistance/2);
        }
    }

    //Collision detection for bullets
    private List<GameObject> BulletvsBullet(GameObject gameObject1, GameObject gameObject2){
        List<GameObject> rmObjects = new ArrayList<>();
        rmObjects.add(gameObject1);
        rmObjects.add(gameObject2);
        runGameView.addAnimation(RunGameView.BULLET_EXPLOSION_ANIMATION, RunGameView.BULLET_EXPLOSION_FRAME_DELAY, gameObject1.getX(), gameObject1.getY());
        runGameView.addAnimation(RunGameView.BULLET_EXPLOSION_ANIMATION, RunGameView.BULLET_EXPLOSION_FRAME_DELAY, gameObject2.getX(), gameObject2.getY());
        return rmObjects;
    }

    //Collision detection for player tanks and bullets
    private List<GameObject> TankvsBullet(GameObject gameObject1, GameObject gameObject2){
        List<GameObject> rmObjects = new ArrayList<>();

        if(((Bullet) gameObject2).getBulletID().equals(gameObject1.getId())){}

        if(!((Bullet) gameObject2).getBulletID().equals(gameObject1.getId()) && ((Tank) gameObject1).getHealthPoint() != 0){

            ((Tank) gameObject1).decreaseHealthPoint();

            rmObjects.add(gameObject2);
            runGameView.addAnimation(RunGameView.BULLET_EXPLOSION_ANIMATION, RunGameView.BULLET_EXPLOSION_FRAME_DELAY, gameObject2.getX(), gameObject2.getY());
        }


        if(!((Bullet) gameObject2).getBulletID().equals(gameObject1.getId()) && ((Tank) gameObject1).getHealthPoint() == 0){

            rmObjects.add(gameObject1);
            rmObjects.add(gameObject2);
            ((Tank) gameObject1).decreaseHealthPoint();
            runGameView.addAnimation(RunGameView.BIG_EXPLOSION_ANIMATION, RunGameView.BIG_EXPLOSION_FRAME_DELAY, gameObject1.getX(), gameObject1.getY());
            runGameView.addAnimation(RunGameView.BULLET_EXPLOSION_ANIMATION, RunGameView.BULLET_EXPLOSION_FRAME_DELAY, gameObject2.getX(), gameObject2.getY());


            if(gameObject1.getId().equals(GameWorld.PLAYER_TANK_ID)){
                playerTankCount--;
                gameWorld.playSound("TankExplosionSound.wav");
            }else if(!gameObject1.getId().equals(GameWorld.PLAYER_TANK_ID)) {
                gameWorld.playSound("TankExplosionSound.wav");
            }

            if(gameObject1.getId().equals(GameWorld.PLAYER_TANK2_ID)){
                playerTankCount--;
                gameWorld.playSound("TankExplosionSound.wav");
            }else if(!gameObject1.getId().equals(GameWorld.PLAYER_TANK2_ID)) {
                gameWorld.playSound("TankExplosionSound.wav");
            }

        }
        return rmObjects;

    }

    //Collision detection for player tanks and walls
    private void TankvsWall(GameObject gameObject1, GameObject gameObject2){
        double distanceA = gameObject1.getXBound() - gameObject2.getX();
        double distanceB = gameObject2.getXBound() - gameObject1.getX();
        double distanceC = gameObject1.getYBound() - gameObject2.getY();
        double distanceD = gameObject2.getYBound() - gameObject1.getY();
        double[] distance = {distanceA,distanceB, distanceC, distanceD};
        Arrays.sort(distance);
        double minDistance = distance[0];

        //Checks the min distance of the hit-box border on each player tank with the game wall
        if(minDistance == distanceA){
            gameObject1.setX (gameObject1.getX() - minDistance);
        }else if(distance[0] == distanceB){
            gameObject1.setX (gameObject1.getX() + minDistance);
        }else if(distance[0] == distanceC){
            gameObject1.setY (gameObject1.getY() - minDistance);
        }else {
            gameObject1.setY (gameObject1.getY() + minDistance);
        }
    }

    //Collision detection for bullets and walls
    private List<GameObject> BulletvsWall(GameObject gameObject1, GameObject gameObject2){
        List<GameObject> rmObjects = new ArrayList<>();

        rmObjects.add(gameObject1);
        runGameView.addAnimation(RunGameView.BULLET_EXPLOSION_ANIMATION, RunGameView.BULLET_EXPLOSION_FRAME_DELAY, gameObject1.getX(), gameObject1.getY());

        if(((BreakableWall) gameObject2).getHealthPoint() != 0){
            ((BreakableWall) gameObject2).decreaseHealthPoint();
        }else if(((BreakableWall) gameObject2).getHealthPoint() == 0){
            rmObjects.add(gameObject2);
            runGameView.addAnimation(RunGameView.BULLET_EXPLOSION_ANIMATION, RunGameView.BULLET_EXPLOSION_FRAME_DELAY, gameObject2.getX(), gameObject2.getY());
            gameWorld.playSound("WallExplosionSound.wav");
        }
        return rmObjects;
    }

    //Collision detection for player tanks and power up items
    private List<GameObject> TankvsPowerUp(GameObject gameObject1, GameObject gameObject2){
        List<GameObject> rmObjects = new ArrayList<>();

        rmObjects.add(gameObject2);
        ((Tank) gameObject1).powerUp();

        gameWorld.playSound("PickUpPowerupSound.wav");
        return rmObjects;
    }


    public class GameActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event){
            String actionCommand = event.getActionCommand();

            if(actionCommand.equals(Start_End_Menu_View.START_BUTTON_ACTION_COMMAND)) {
                UIMenuView.setScreen(UI_Menu_View.Screen.RUN_GAME_SCREEN);
                runGame();
            }else if(actionCommand.equals(Start_End_Menu_View.EXIT_BUTTON_ACTION_COMMAND)){
                UIMenuView.closeGame();
            }
        }
    }

    private void resetGameScreen(){ //Reset game world/objects to their initial state/locations
        UIMenuView.setScreen(UI_Menu_View.Screen.END_MENU_SCREEN);
        gameWorld.reset();
        runGameView.reset();
        playerTankCount = 0;
    }



    public static void main(String[] args) {
        GameDriver gameDriver = new GameDriver();
        gameDriver.start();
    }
}
