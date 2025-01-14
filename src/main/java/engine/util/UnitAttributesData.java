package engine.util;

public class UnitAttributesData {
    private String unit_type;
    private int unit_speed;


    public UnitAttributesData(String unit_type, int unit_speed) {
        this.unit_type = unit_type;
        this.unit_speed = unit_speed;
    }

    public String getUnit_type() {
        return unit_type;
    }

    public void setUnit_type(String unit_type) {
        this.unit_type = unit_type;
    }

    public int getUnit_speed() {
        return unit_speed;
    }

    public void setUnit_speed(int unit_speed) {
        this.unit_speed = unit_speed;
    }

    @Override
    public String toString() {
        return "UnitSpeedData{" +
                "unit_type='" + unit_type + '\'' +
                ", unit_speed=" + unit_speed +
                '}';
    }
}

