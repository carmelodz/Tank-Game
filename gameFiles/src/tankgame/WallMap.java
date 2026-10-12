package tankgame;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WallMap { //Class to handle wall positions for the game map
    private static final String WALL_IMAGE_FILE_PREFIX = "wall-";
    private static final String WALL_IMAGE_FILE_SUFFIX = ".png";
    private static final String WALLS_SETUP_FILE = "walls.txt";
    private static final double WALL_WIDTH = 32.0;
    private static final double WALL_HEIGHT = 32.0;

    private final String imageFile;
    private final double x;
    private final double y;

    private WallMap(String imageFile, double x, double y) {
        this.imageFile = imageFile;
        this.x = x;
        this.y = y;
    }

    public String getImageFile() {
        return imageFile;
    }


    public double getX() {
        return x;
    }


    public double getY() {
        return y;
    }

    public static List<WallMap> readWalls() {
        ArrayList<ArrayList<Integer>> walls;
        URL fileUrl = WallMap.class.getClassLoader().getResource(WALLS_SETUP_FILE);
        if (fileUrl == null) {
            throw new RuntimeException("Unable to find the resource: " + WALLS_SETUP_FILE);
        }

        try {
            walls =
                    Files.lines(Path.of(fileUrl.toURI()))
                            .map(WallMap::lineToList)
                            .collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException | URISyntaxException exception) {
            throw new RuntimeException(exception);
        }

        ArrayList<WallMap> wallMap = new ArrayList<>();
        for (int numRow = 0; numRow < walls.size(); numRow++) {
            for (int numCol = 0; numCol < walls.get(numRow).size(); numCol++) {
                if (walls.get(numRow).get(numCol) != 0) {
                    double x = numCol * WALL_WIDTH;
                    double y = numRow * WALL_HEIGHT;
                    wallMap.add(
                            new WallMap(
                                    String.format("%s%d%s", WALL_IMAGE_FILE_PREFIX, walls.get(numRow).get(numCol), WALL_IMAGE_FILE_SUFFIX),
                                    x,
                                    y));
                }
            }
        }
        return wallMap;
    }

    private static ArrayList<Integer> lineToList(String line) {
        return Arrays.stream(line.split("\\s")).map(Integer::parseInt).collect(Collectors.toCollection(ArrayList::new));
    }
}