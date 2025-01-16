package engine.util;

import java.awt.*;

public class Unit {

    private String type;
    private int x, y, health;
    private Image image;
    private PathHandle pathHandle;
    private Vector currentVector;
    private int direction;

    public Unit(String type, int initHealth, int x, int y, Image image) {

        this.type = type;
        this.x = x;
        this.y = y;
        health = initHealth;
        this.image = image;
        pathHandle = new PathHandle();

    }


    public void setCurrentVector(Vector vector) {currentVector = vector;}
    public void setDirection(int dir) {direction = dir;}
    public Vector getCurrentVector() {return currentVector;}
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
    public void moveTo(Vector vector, int[] map) {
        setDirection((vector.getDir() + 360) % 360);
        System.out.println(vector.getDir());
        double angleRadians = Math.toRadians(direction);
        double xMovement = vector.getDist() * Math.cos(angleRadians);
        double yMovement = vector.getDist() * Math.sin(angleRadians);

        setX(x + (int) xMovement);
        setY(y + (int) yMovement);

    }
}
