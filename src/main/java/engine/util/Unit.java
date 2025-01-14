package engine.util;

import java.awt.*;

public class Unit {

    private String type;
    private int x, y, health;
    private Image image;
    private PathHandle pathHandle;

    public Unit(String type, int initHealth, int x, int y, Image image) {

        this.type = type;
        this.x = x;
        this.y = y;
        health = initHealth;
        this.image = image;
        pathHandle = new PathHandle();

    }


    public int getX() {return x;}
    public int getY() {return y;}
    public int getInitHealth() {return health;}
    public String getType() {return type;}
    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}
    public void setHealth(int health) {this.health = health;}
    public void setType(String type) {this.type = type;}
    public void setPos(int x, int y) {this.x = x; this.y = y;}
    public Image getImage() {return image;}
    public void moveTo(int goalX, int goalY, int[] map) {
        double currentX = x;
        double currentY = y;
        double deltaX = goalX - currentX;
        double deltaY = goalY - currentY;

        double angleRadians = Math.atan2(deltaY, deltaX);
        double angleDegrees = Math.toDegrees(angleRadians);
        if (angleDegrees < 0) {
            angleDegrees += 360;
        }
        if(pathHandle.isMovementPossible(map , (int) angleDegrees)) {

        }
    }
}
