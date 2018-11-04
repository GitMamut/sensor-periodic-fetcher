package mintfrost.cloud.persistance;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class WriteToFile implements Persister {

    @Override
    public void save(ResponseEntity<String> responseEntity) {
        Map<String, Map<String, Object>> response = unmarshall(responseEntity);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("/tmp/test_write_file.txt", true))) {
            writer.append(response.toString()).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<String, Map<String, Object>> unmarshall(ResponseEntity<String> responseEntity) {
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Map<String, Object>> responseObject = null;
        try {
            responseObject = objectMapper.readValue(responseEntity.getBody(), new TypeReference<Map<String, Map<String, Object>>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(responseObject);
        return responseObject;
    }
}
