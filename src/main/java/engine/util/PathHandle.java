package engine.util;

public class PathHandle {

    private final int unitPos = 3;
    private int[] areaMap;
    private int direction;

    public PathHandle() {

    }

    public boolean isMovementPossible(int[] areaMap, int direction) {
        this.direction = direction/45;
        this.areaMap = areaMap;
        boolean movement = false;

        switch (direction) {
            case 1: if(areaMap[1] == 0) {movement = true;}
            case 2: if(areaMap[2] == 0) {movement = true;}
            case 3: if(areaMap[3] == 0) {movement = true;}
            case 4: if(areaMap[6] == 0) {movement = true;}
            case 5: if(areaMap[9] == 0) {movement = true;}
            case 6: if(areaMap[8] == 0) {movement = true;}
            case 7: if(areaMap[7] == 0) {movement = true;}
            case 8: if(areaMap[4] == 0) {movement = true;}
        }

        return movement;
    }



}
