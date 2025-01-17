package engine.util;

import java.util.ArrayList;
import java.util.List;

public class UnitHandle {

    private List<Unit> units;

    public UnitHandle() {

        units = new ArrayList<>();

    }

    public void addUnit(Unit unit) {units.add(unit);}
    public void removeUnit(int unit) {units.remove(unit);}
    public Unit getUnit(int index) {return units.get(index);}
    public int getUnitX(int index) {return units.get(index).getX();}
    public int getUnitY(int index) {return units.get(index).getY();}
    public List<Unit> getUnits() {return units;}

    public void moveUnits(List<Unit> selectedUnits, int goalX, int goalY, MapData mapData) {

            for (int i = 0; i < selectedUnits.size(); i++) {
                int unitX = selectedUnits.get(i).getX();
                int unitY = selectedUnits.get(i).getY();
                int[] local = new int[9];
                int[][] groundLayer = mapData.getGroundLayer();
                int index = 0;
                for (int dy = -1; dy <= 1; dy++) {
                    for (int dx = -1; dx <= 1; dx++) {
                        int neighborX = unitX + dx;
                        int neighborY = unitY + dy;

                        if (neighborX >= 0 && neighborX < groundLayer.length && neighborY >= 0 && neighborY < groundLayer[0].length) {
                            local[index] = groundLayer[neighborY][neighborX];
                        } else {local[index] = -1;}
                        index++;
                    }
                }

                for(int tile : local) {System.out.println(tile + " ");}

                System.out.println(selectedUnits.get(i).getType() + " moved");
                Vector vector = new Vector();
                System.out.println(vector.getDir() + " " + vector.getDist());
                vector.setVector(selectedUnits.get(i).getX(), selectedUnits.get(i).getY(), goalX, goalY);
                selectedUnits.get(i).moveTo(vector, local);

        }

    }

}
