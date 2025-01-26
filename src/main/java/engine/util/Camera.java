package engine.util;

public class Camera {

    private int x, y;
    private int startX, startY;

    public Camera(int x, int y) {

        this.x = x;
        this.y = y;

    }

    public void drag(int x, int y) {startX = x; startY = y;}
    public int getDragX() {return x - startX;}
    public int getDragY() {return y - startY;}
    public int getMapX(int posX) {return posX - x;}
    public int getMapY(int posY) {return posY - y;}
    public int getX() {return x;}
    public int getY() {return y;}
    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}

}
