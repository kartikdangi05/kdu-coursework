package assignment_one;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JSONProcessor {

    private JSONProcessor(){}
    public static JsonNode readJSON(String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readTree(new File(filePath));
        } catch (IOException e) {
            Logging.logErr("Error reading JSON!");
            return null;
        }
    }
}
