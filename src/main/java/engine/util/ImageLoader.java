package engine.util;

import java.awt.*;

public class ImageLoader {

    private String pathToRes = "src/main/java/engine/res/";

    public ImageLoader() {

    }
    public Image getImage(String filename) {return Toolkit.getDefaultToolkit().getImage(pathToRes + filename);}
}
