package tankgame.gameScreen;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

class DrawableObject { //Class for reading image files and handling drawable objects
    private static final Map<String, BufferedImage> imageCache = new HashMap<>();

    private final BufferedImage objectImage;
    private final AffineTransform affineTransform;
    private final String filePath;

    DrawableObject(String imageFile) {
        filePath = imageFile;
        if (!imageCache.containsKey(imageFile)) {
            URL imageUrl = getClass().getClassLoader().getResource(imageFile);
            if (imageUrl == null) {
                throw new RuntimeException("Object image file not found");
            }
            try {
                imageCache.put(imageFile, ImageIO.read(imageUrl));
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }

        objectImage = imageCache.get(imageFile);
        affineTransform = new AffineTransform();
    }

    String getFilePath() {
        return filePath;
    }

    BufferedImage getObjectImage() {
        return objectImage;
    }

    AffineTransform getAffineTransform() {
        return affineTransform;
    }

    void setLocationAndAngle(double x, double y, double angle) {
        affineTransform.setToTranslation(x, y);
        affineTransform.rotate(angle, objectImage.getWidth() / 2.0, objectImage.getHeight() / 2.0);
    }
}