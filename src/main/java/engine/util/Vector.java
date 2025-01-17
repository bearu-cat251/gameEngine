package engine.util;

public class Vector {

    private int dist, dir;



    public Vector() {}
    public void setVector(int unitX, int unitY, int deltaX, int deltaY) {

        dist = (int) Math.abs(Math.sqrt(((deltaX -  unitX) * (deltaX - unitX)) + (deltaY-unitY) * (deltaY - unitY)));
        dir = (int) Math.atan2(deltaY - unitY, deltaX - unitX);
        System.out.println("New Vector Distance: " + dist);
        System.out.println("New Vector Dir: " + dir);
    }

    public int getDist() {return dist;}
    public int getDir() {return dir;}

}
