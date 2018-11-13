package mintfrost.cloud;

import mintfrost.cloud.persistance.DisplayToOled;
import mintfrost.cloud.persistance.Persister;
import mintfrost.cloud.persistance.SaveToDbCommon;
import mintfrost.cloud.persistance.WriteToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Value("${fetcher.sourceUrl}")
    private String sourceUrl;

    @Value("${persister.targetUrl}")
    private String targetUrl;

    @Value("${fixedRate.milliseconds}")
    private String rate;

    @Autowired
    Persister persister;

    @Bean
    PeriodicFetcher periodicFetcher() {
        System.out.println("fixedRate.milliseconds: " + rate);
        return new PeriodicFetcher(sourceUrl, persister);
    }

    @Bean
    @ConditionalOnProperty(name="persister.class", havingValue="SaveToDbCommon")
    Persister saveToDbCommon() {
        return new SaveToDbCommon(targetUrl);
    }

    @Bean
    @ConditionalOnProperty(name="persister.class", havingValue="DisplayToOled")
    Persister displayToOled() {
        return new DisplayToOled(targetUrl);
    }

    @Bean
    @ConditionalOnProperty(name="persister.class", havingValue="WriteToFile")
    Persister writeToFile() {
        return new WriteToFile(targetUrl);
    }


}
