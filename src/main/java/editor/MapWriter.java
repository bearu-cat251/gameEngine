package editor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;

public class MapWriter {
    private final ObjectMapper objectMapper;

    public MapWriter() {
        this.objectMapper = new ObjectMapper();
    }

    public void writeMapToFile(
            int width,
            int height,
            int[][] groundLayer,
            int[][] objectLayer,
            int[][] resourceLayer,
            String filePath
    ) throws IOException {

        ObjectNode rootNode = objectMapper.createObjectNode();

        rootNode.put("width", width);
        rootNode.put("height", height);

        rootNode.set("groundLayer", createArrayNode(groundLayer));
        rootNode.set("objectLayer", createArrayNode(objectLayer));
        rootNode.set("resourceLayer", createArrayNode(resourceLayer));

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), rootNode);
    }

    private ArrayNode createArrayNode(int[][] layer) {
        ArrayNode arrayNode = objectMapper.createArrayNode();
        for (int[] row : layer) {
            ArrayNode rowNode = objectMapper.createArrayNode();
            for (int cell : row) {
                rowNode.add(cell);
            }
            arrayNode.add(rowNode);
        }
        return arrayNode;
    }


}

