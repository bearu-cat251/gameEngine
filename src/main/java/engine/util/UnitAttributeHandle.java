package engine.util;

import com.google.gson.*;
import java.io.*;
import java.util.*;

public class UnitAttributeHandle {

    private static final String FILE_NAME = "C:/Users/meirm/IdeaProjects/GameEngineGradle/src/main/java/engine/util/unit_attributes.json";

    public static List<UnitAttributesData> readUnitSpeedData() {

        try (Reader reader = new FileReader(FILE_NAME)) {

            Gson gson = new Gson();
            UnitAttributesData[] unitSpeedDataArray = gson.fromJson(reader, UnitAttributesData[].class);
            return Arrays.asList(unitSpeedDataArray);

        } catch (IOException e) {
            e.printStackTrace();
        }


        return new ArrayList<>();
    }

    public ArrayList<UnitAttributesData> getUnitAttributes() {

        List<UnitAttributesData> readData = readUnitSpeedData();
        System.out.println("Read data from JSON file:");

        for (UnitAttributesData data : readData) {
            System.out.println(data);
        }

        return new ArrayList<>(readData);
    }

    public int getUnitSpeedByType(String unitType) {
        List<UnitAttributesData> unitDataList = readUnitSpeedData();

        for (UnitAttributesData data : unitDataList) {
            if (data.getUnit_type().equalsIgnoreCase(unitType)) {
                return data.getUnit_speed();
            }
        }
        System.out.println("Unit type '" + unitType + "' not found.");
        return -1;

    }
}
