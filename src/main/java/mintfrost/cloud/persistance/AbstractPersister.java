package mintfrost.cloud.persistance;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import mintfrost.cloud.response.CurrentOutdoorResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

abstract class AbstractPersister<T> implements Persister {

    T unmarshall(ResponseEntity<String> responseEntity) {
        ObjectMapper objectMapper = new ObjectMapper();

        T responseObject = null;
        try {
            responseObject = objectMapper.readValue(responseEntity.getBody(), new TypeReference<T>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(responseObject);
        return responseObject;
    }
}
