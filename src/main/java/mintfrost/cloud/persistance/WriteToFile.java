package mintfrost.cloud.persistance;

import mintfrost.cloud.dto.CommonSensorResponse;
import org.springframework.http.ResponseEntity;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile extends AbstractPersister {

    private final String targetUrl;

    public WriteToFile(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    @Override
    public void save(ResponseEntity<String> responseEntity) {
        CommonSensorResponse response = unmarshall(responseEntity);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(targetUrl, true))) {
            writer.append(response.toString()).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "WriteToFile{" +
                "targetUrl='" + targetUrl + '\'' +
                '}';
    }
}
