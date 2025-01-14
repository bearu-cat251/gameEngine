package engine.util;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;

public class ReadMap {

    public MapData readMap(String filePath) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath)) {

            MapData mapData = gson.fromJson(reader, MapData.class);
            return mapData;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

