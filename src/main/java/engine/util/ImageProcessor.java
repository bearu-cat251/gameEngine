package engine.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class ImageProcessor {


    public Image getSubImage(Image img, int x, int y, int width, int height) {

        BufferedImage bufferedImage = toBufferedImage(img);

        if (x < 0 || y < 0 || x + width > bufferedImage.getWidth() || y + height > bufferedImage.getHeight()) {
            throw new IllegalArgumentException("Subimage dimensions are out of bounds.");
        }

        BufferedImage subImage = bufferedImage.getSubimage(x, y, width, height);

        return (Image) subImage;
    }

    private BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        BufferedImage bufferedImage = new BufferedImage(
                img.getWidth(null),
                img.getHeight(null),
                BufferedImage.TYPE_INT_ARGB
        );


        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        return bufferedImage;
    }
}
