package ton.telegrambots.ecommarket.testproject.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@ConfigurationProperties(prefix = "custom.throttling")
public class ThrottlingProperties {
    private Integer countLimit;
    private Duration timeLimit;

    public Integer getCountLimit() {
        return countLimit;
    }

    public void setCountLimit(Integer countLimit) {
        this.countLimit = countLimit;
    }

    public Duration getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Duration timeLimit) {
        this.timeLimit = timeLimit;
    }
}
