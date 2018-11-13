package mintfrost.cloud.persistance;

import mintfrost.cloud.dto.CommonSensorResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DisplayToOled extends AbstractPersister {

    private static DateFormat sdf = new SimpleDateFormat("yyyyMMddHH:mm:ss");
    private final String targetUrl;

    public DisplayToOled(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    @Override
    public void save(ResponseEntity<String> responseEntity) {
        final CommonSensorResponse response = unmarshall(responseEntity);
        displayToOled(response);
    }

    private void displayToOled(CommonSensorResponse commonSensorResponse) {
        HttpEntity<String> requestEntity1 = new HttpEntity<>("", getDefaultHeaders());
        final String targetUrlService1 = targetUrl + "?cmd=OLED,3,1," + sdf.format(commonSensorResponse.getDate());
        System.out.println("RQ: " + targetUrlService1);
        ResponseEntity<String> responseEntity1 = REST_TEMPLATE.exchange(targetUrlService1, HttpMethod.GET, requestEntity1, String.class);
        System.out.println("RS: " + responseEntity1);

        HttpEntity<String> requestEntity = new HttpEntity<>("", getDefaultHeaders());
        final String targetUrlService = targetUrl + "?cmd=OLED,4,1,Outside: " + commonSensorResponse.getValues().get(0).getValue().replace('.', '/');
        System.out.println("RQ: " + targetUrlService);
        ResponseEntity<String> responseEntity = REST_TEMPLATE.exchange(targetUrlService, HttpMethod.GET, requestEntity, String.class);
        System.out.println("RS: " + responseEntity);
    }
}
