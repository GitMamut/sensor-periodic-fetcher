package mintfrost.cloud;

import mintfrost.cloud.persistance.DisplayToOled;
import mintfrost.cloud.persistance.WriteToFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Value("${targetUrl}")
    private String targetUrl;

    @Value("${fixedRate.milliseconds}")
    private String rate;

    @Bean PeriodicFetcher periodicFetcher() {
        System.out.println("fixedRate.milliseconds: " + rate);
        return new PeriodicFetcher(targetUrl, new DisplayToOled());
    }
}
