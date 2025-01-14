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

}
