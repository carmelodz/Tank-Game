package tankgame.gameScreen;

import tankgame.gameObjects.Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class RunGameView extends JPanel { //JPanel class responsible for executing and running the game screen and UI
    public static final Dimension SCREEN_DIMENSIONS = new Dimension(1024, 768);

    public static final String PLAYER_TANK_IMAGE_FILE = "player-tank.png";
    public static final double PLAYER_TANK_INITIAL_X = 410.0;
    public static final double PLAYER_TANK_INITIAL_Y = 400.0;
    public static final double PLAYER_TANK_INITIAL_ANGLE = Math.toRadians(180.0);

    public static final String PLAYER_TANK2_IMAGE_FILE = "player-tank2.png";
    public static final double PLAYER_TANK2_INITIAL_X = 560.0;
    public static final double PLAYER_TANK2_INITIAL_Y = 250.0;
    public static final double PLAYER_TANK2_INITIAL_ANGLE = Math.toRadians(0.0);

    public static final String POWER_UP_IMAGE_FILE = "Bouncing.gif";
    public static final double POWER_UP_INITIAL_X = 500.0;
    public static final double POWER_UP_INITIAL_Y = 50.0;
    public static final double POWER_UP_INITIAL_ANGLE = 0;

    public static final String BULLET_IMAGE_FILE = "bullet.png";

    private static final String BULLET_EXPLOSION_FILE_PREFIX = "bullet-explosion-";
    private static final String BULLET_EXPLOSION_FILE_SUFFIX = ".png";

    public static final ResourceLoader BULLET_EXPLOSION_ANIMATION =
            new ResourceLoader(BULLET_EXPLOSION_FILE_PREFIX, BULLET_EXPLOSION_FILE_SUFFIX, 6);
    public static final int BULLET_EXPLOSION_FRAME_DELAY = 3;
    public static final double BULLET_EXPLOSION_WIDTH = 32.0;
    public static final double BULLET_EXPLOSION_HEIGHT = 32.0;

    private static final String BIG_EXPLOSION_FILE_PREFIX = "big-explosion-";
    private static final String BIG_EXPLOSION_FILE_SUFFIX = ".png";

    public static final ResourceLoader BIG_EXPLOSION_ANIMATION =
            new ResourceLoader(BIG_EXPLOSION_FILE_PREFIX, BIG_EXPLOSION_FILE_SUFFIX, 7);
    public static final int BIG_EXPLOSION_FRAME_DELAY = 4;
    public static final double BIG_EXPLOSION_WIDTH = 64.0;
    public static final double BIG_EXPLOSION_HEIGHT = 64.0;

    private final BufferedImage worldImage;
    private final Map<String, DrawableObject> drawableObjectsById = new HashMap<>();
    private final List<Animation> animations = new LinkedList<>();

    private int playerHP;
    private double playerSP;
    private int player2HP;
    private double player2SP;


    public RunGameView() {
        worldImage = new BufferedImage(SCREEN_DIMENSIONS.width, SCREEN_DIMENSIONS.height, BufferedImage.TYPE_INT_RGB);
        setBackground(Color.BLACK);
    }

    public void reset() {
        drawableObjectsById.clear();
    }

    public void addDrawableObject(
            String id, String objectImageFile, double initialX, double initialY, double initialAngle) {
        DrawableObject drawableObject = new DrawableObject(objectImageFile);
        drawableObject.setLocationAndAngle(initialX, initialY, initialAngle);
        drawableObjectsById.put(id, drawableObject);
    }

    public void removeDrawableObject(String id) {
        drawableObjectsById.remove(id);
    }

    public void setDrawableObjectLocationAndAngle(String id, double x, double y, double angle) {
        drawableObjectsById.get(id).setLocationAndAngle(x, y, angle);
    }

    public void addAnimation(ResourceLoader resourceLoader, int frameDelay, double x, double y) {
        animations.add(new Animation(resourceLoader, frameDelay, x, y));
    }

    public void showPlayerTank1HP(Tank playerTank){
        playerHP = playerTank.getHealthPoint();
        playerSP = playerTank.getSpeed();

    }

    public void showPlayerTank2HP(Tank playerTank2){
        player2HP = playerTank2.getHealthPoint();
        player2SP = playerTank2.getSpeed();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D buffer = worldImage.createGraphics();
        buffer.setColor(Color.GRAY);
        buffer.fillRect(0, 0, SCREEN_DIMENSIONS.width, SCREEN_DIMENSIONS.height);

        //Render the left-half and right-half of game screen
        BufferedImage leftHalf = worldImage.getSubimage(0, 0, SCREEN_DIMENSIONS.width/2, SCREEN_DIMENSIONS.height);
        BufferedImage rightHalf = worldImage.getSubimage(512,0, SCREEN_DIMENSIONS.width/2, SCREEN_DIMENSIONS.height);

        Map<String, DrawableObject> newDrawableObjectsById = new HashMap<>();
        Boolean needTry = true;
        while (needTry) {

            try {
                newDrawableObjectsById = new HashMap<>();
                newDrawableObjectsById.putAll(drawableObjectsById);
            } catch (ConcurrentModificationException e) {
                continue;
            }
            needTry = false;
        }

        for (DrawableObject drawableObject : newDrawableObjectsById.values()) {
            buffer.drawImage(drawableObject.getObjectImage(), drawableObject.getAffineTransform(), null);
        }

        ListIterator<Animation> animationIterator = animations.listIterator();
        while (animationIterator.hasNext()) {
            Animation animation = animationIterator.next();
            Optional<BufferedImage> nextImage = animation.getImage();
            if (nextImage.isPresent()) {
                buffer.drawImage(nextImage.get(), (int) animation.getX(), (int) animation.getY(), null);
            } else {
                animationIterator.remove();
            }
        }

        //Shows entire game screen
        g.drawImage(worldImage, 0, 0, null);

        //Shows mini world map
        g.drawImage(this.worldImage.getScaledInstance(255,150,worldImage.SCALE_SMOOTH),380,590,null);

        //Shows left and right half of game screen
        g.drawImage(leftHalf, 0, 0, null);
        g.drawImage(rightHalf, 512, 0, null);

        //Shows Tank Health Bar
        g.setColor(Color.green);
        g.fillRect(20, 700, playerHP, 100);
        g.setColor(Color.white);// Draws health bar border
        g.drawRect(20, 700, 200, 100);

        g.setColor(Color.yellow);
        g.fillRect(800, 700, player2HP, 100);
        g.setColor(Color.white);// Draws health bar border
        g.drawRect(800, 700, 200, 100);

        g.setFont(new Font("Arial Black", Font.BOLD, 14));

        //Shows Tank life count and current tank speed
        g.setColor(Color.GREEN);
        g.drawString("PLAYER TANK1 HP: " + playerHP, 20, 650 );
        g.drawString("PLAYER TANK1 SPEED: " + playerSP, 20, 600 );

        g.setColor(Color.yellow);
        g.drawString("PLAYER TANK2 HP: " + player2HP, 800, 650 );
        g.drawString("PLAYER TANK2 SPEED: " + player2SP, 800, 600 );

    }
}