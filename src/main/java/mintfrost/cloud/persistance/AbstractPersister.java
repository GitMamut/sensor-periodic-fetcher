package mintfrost.cloud.persistance;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import mintfrost.cloud.dto.CommonSensorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

abstract class AbstractPersister implements Persister {

    static final RestTemplate REST_TEMPLATE = new RestTemplate();

    static HttpHeaders getDefaultHeaders() {
        final HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        headers.add(HttpHeaders.ACCEPT, "*/*");
        return headers;
    }

    CommonSensorResponse unmarshall(final ResponseEntity<String> responseEntity) {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

        CommonSensorResponse responseObject = null;
        try {
            responseObject = objectMapper.readValue(responseEntity.getBody(), new TypeReference<CommonSensorResponse>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("RS: " + responseObject);
        return responseObject;
    }
}
