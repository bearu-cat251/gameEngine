package editor;

import java.io.IOException;

public class Saver {
    public Saver() {

    }

    public void saveFile(String filename) {

        System.out.println("Started");

        MapWriter mapWriter = new MapWriter();

        int[][] groundLayer = {
                {9, 9, 9, 9, 9},
                {9, 1, 2, 3, 9},
                {9, 8, 0, 4, 9},
                {9, 7, 6, 5, 9},
                {9, 9, 9, 9, 9}
        };

        int[][] objectLayer = {
                {0, 0, 0, 0, 0},
                {0, 2, 2, 0, 0},
                {0, 2, 0, 0, 0},
                {0, 0, 0, 3, 0},
                {0, 0, 0, 0, 0}
        };

        int[][] resourceLayer = {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };

        try {
            mapWriter.writeMapToFile(5, 5, groundLayer, objectLayer, resourceLayer, "src/main/java/editor/res/" + filename);
            System.out.println("Map written to file successfully!");
        } catch (
                IOException e) {
            e.printStackTrace();
        }

    }
}
