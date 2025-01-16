package engine.util;

public class Vector {

    private int dist, dir;



    public Vector() {}
    public void setVector(int unitX,int unitY,int deltaX,int deltaY){

        dist = (int) Math.sqrt(((deltaX-unitX) * (deltaX-unitX)) + (deltaY-unitY) * (deltaY-unitY));
        dir = (int) Math.atan2(deltaY-unitY, deltaX-unitX);
    }

    public int getDist() {return dist;}
    public int getDir() {return dir;}

}//bananana
