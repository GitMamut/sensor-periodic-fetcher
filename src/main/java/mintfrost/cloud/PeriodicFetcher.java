package mintfrost.cloud;

import mintfrost.cloud.persistance.Persister;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Component
@RestController
public class PeriodicFetcher {

    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    private final String sourceUrl;
    private final Persister persister;

    public PeriodicFetcher(String sourceUrl, Persister persister) {
        this.sourceUrl = sourceUrl;
        this.persister = persister;
        System.out.println("Starting: " + this.toString());
    }

    @RequestMapping("/persist")
    @Scheduled(fixedRateString = "${scheduler.fixedRate.milliseconds}")
    private void fetchSensor() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        headers.add(HttpHeaders.ACCEPT, "*/*");


        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        System.out.println("RQ: " + sourceUrl);
        ResponseEntity<String> responseEntity = REST_TEMPLATE.exchange(sourceUrl, HttpMethod.GET, requestEntity, String.class);

        persister.save(responseEntity);
    }

    @Override
    public String toString() {
        return "PeriodicFetcher{" +
                "sourceUrl='" + sourceUrl + '\'' +
                ", persister=" + persister +
                '}';
    }
}
