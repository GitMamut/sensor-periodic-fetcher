package mintfrost.cloud.persistance;

import org.springframework.http.ResponseEntity;

public interface Persister {
    void save(ResponseEntity<String> responseEntity);
}
