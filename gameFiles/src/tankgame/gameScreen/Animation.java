package tankgame.gameScreen;

import java.awt.image.BufferedImage;
import java.util.Optional;

class Animation { //Class to handle game animations
    private final ResourceLoader resourceLoader;
    private final int frameDelay;
    private final double x;
    private final double y;

    private int currentFrame = 0;
    private int counter = 0;

    Animation(ResourceLoader resourceLoader, int frameDelay, double x, double y) {
        this.resourceLoader = resourceLoader;
        this.frameDelay = frameDelay;
        this.x = x;
        this.y = y;
    }

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }

    Optional<BufferedImage> getImage() {
        if (currentFrame == resourceLoader.getNumFrames()) {
            return Optional.empty();
        }

        BufferedImage bufferedImage = resourceLoader.getFrame(currentFrame);
        counter++;
        if (counter >= frameDelay) {
            counter = 0;
            currentFrame++;
        }

        return Optional.of(bufferedImage);
    }
}