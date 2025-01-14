package engine.util;

import java.awt.*;

public class ImageLoader {

    private String pathToRes = "C:/Users/meirm/IdeaProjects/GameEngineGradle/src/main/java/engine/res/";

    public ImageLoader() {

    }
    public Image getImage(String filename) {return Toolkit.getDefaultToolkit().getImage(pathToRes + filename);}
}
