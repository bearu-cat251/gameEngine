package engine.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.math.*;

public class ImageProcessor {


    public Image getSubImage(Image img, int x, int y, int width, int height) {

        BufferedImage bufferedImage = toBufferedImage(img);

        if (x < 0 || y < 0 || x + width > bufferedImage.getWidth() || y + height > bufferedImage.getHeight()) {
            throw new IllegalArgumentException("Subimage dimensions are out of bounds.");
        }

        BufferedImage subImage = bufferedImage.getSubimage(x, y, width, height);

        return (Image) subImage;
    }

    public BufferedImage toBufferedImage(Image img) {
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

    public BufferedImage rotateImage(BufferedImage img, double angle) {
        double radians = Math.toRadians(angle);
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.round(w * Math.abs(Math.cos(radians)) + h * Math.abs(Math.sin(radians)));
        int newHeight = (int) Math.round(h * Math.abs(Math.cos(radians)) + w * Math.abs(Math.sin(radians)));

        BufferedImage rotatedImage = new BufferedImage(newWidth, newHeight, img.getType());
        Graphics2D g2d = rotatedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.translate((newWidth - w) / 2, (newHeight - h) / 2);
        g2d.rotate(radians, w / 2.0, h / 2.0);
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        return rotatedImage;
    }
}
