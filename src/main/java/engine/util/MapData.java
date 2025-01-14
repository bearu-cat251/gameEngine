package engine.util;

public class MapData {
    private int width;
    private int height;
    private int[][] groundLayer;
    private int[][] objectLayer;
    private int[][] resourceLayer;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int[][] getGroundLayer() {
        return groundLayer;
    }

    public void setGroundLayer(int[][] groundLayer) {
        this.groundLayer = groundLayer;
    }

    public int[][] getObjectLayer() {
        return objectLayer;
    }

    public void setObjectLayer(int[][] objectLayer) {
        this.objectLayer = objectLayer;
    }

    public int[][] getResourceLayer() {
        return resourceLayer;
    }

    public void setResourceLayer(int[][] resourceLayer) {
        this.resourceLayer = resourceLayer;
    }
}

