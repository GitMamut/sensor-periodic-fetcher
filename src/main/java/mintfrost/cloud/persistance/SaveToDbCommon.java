package mintfrost.cloud.persistance;

import mintfrost.cloud.dto.CommonDbEntity;
import mintfrost.cloud.dto.CommonSensorResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public class SaveToDbCommon extends AbstractPersister {
    private final String targetUrl;

    public SaveToDbCommon(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    @Override
    public void save(ResponseEntity<String> responseEntity) {
        final CommonSensorResponse response = unmarshall(responseEntity);
        saveToDb(response);
    }

    private void saveToDb(CommonSensorResponse commonSensorResponse) {
        for (CommonSensorResponse.CommonSensorValue value : commonSensorResponse.getValues()) {
            try {
                String targetUrlService = targetUrl + "/" + commonSensorResponse.getName() + "-" + value.getName();
                HttpEntity<CommonDbEntity> requestEntity = new HttpEntity<>(
                        new CommonDbEntity(commonSensorResponse.getDate(), value.getValue()),
                        getDefaultHeaders());
                System.out.println("RQ: " + targetUrlService);
                ResponseEntity<String> responseEntity = REST_TEMPLATE.exchange(targetUrlService, HttpMethod.POST, requestEntity, String.class);
                System.out.println("RS: " + responseEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "SaveToDbCommon{" +
                "targetUrl='" + targetUrl + '\'' +
                '}';
    }
}
