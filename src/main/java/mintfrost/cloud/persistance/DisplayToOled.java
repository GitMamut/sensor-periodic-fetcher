package mintfrost.cloud.persistance;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import mintfrost.cloud.response.CurrentOutdoorResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

public class DisplayToOled implements Persister {

    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    @Override
    public void save(ResponseEntity<String> responseEntity) {
        final List<CurrentOutdoorResponse> response = unmarshall(responseEntity);
        response.stream().findFirst().ifPresent(this::displayToOled);
    }

    private void displayToOled(CurrentOutdoorResponse currentOutdoorResponse) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        headers.add(HttpHeaders.ACCEPT, "*/*");



        HttpEntity<String> requestEntity1 = new HttpEntity<>("", headers);
        final String targetUrl1 = "http://espeasy/control?cmd=OLED,3,1,"+currentOutdoorResponse.getDate().replace("-","");
        System.out.println("RQ: " + targetUrl1);
        ResponseEntity<String> responseEntity1 = REST_TEMPLATE.exchange(targetUrl1, HttpMethod.GET, requestEntity1, String.class);
        System.out.println("RS: " + responseEntity1);

        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        final String targetUrl = "http://espeasy/control?cmd=OLED,4,1,Outside: "+currentOutdoorResponse.getValue().replace('.','/');
        System.out.println("RQ: " + targetUrl);
        ResponseEntity<String> responseEntity = REST_TEMPLATE.exchange(targetUrl, HttpMethod.GET, requestEntity, String.class);
        System.out.println("RS: " + responseEntity);

    }

    private List<CurrentOutdoorResponse> unmarshall(final ResponseEntity<String> responseEntity) {
        final ObjectMapper objectMapper = new ObjectMapper();

        List<CurrentOutdoorResponse> responseObject = null;
        try {
            //CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, CurrentOutdoorResponse.class);
            //responseObject = objectMapper.readValue(responseEntity.getBody(), collectionType);
            responseObject = objectMapper.readValue(responseEntity.getBody(), new TypeReference<List<CurrentOutdoorResponse>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("RS: " + responseObject);
        return responseObject;
    }
}
