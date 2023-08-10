package ton.telegrambots.ecommarket.testproject.service;

import org.springframework.stereotype.Component;
import ton.telegrambots.ecommarket.testproject.config.ThrottlingProperties;

@Component
public class ThrottlingServiceImpl implements ThrottlingService {

    private final ThrottlingProperties properties;
    private final RequestLimitCheckService service;

    public ThrottlingServiceImpl(ThrottlingProperties properties, RequestLimitCheckService service) {
        this.properties = properties;
        this.service = service;
    }

    @Override
    public void enforce(String remoteAddress) {
        if (limitExceed(remoteAddress)) {
            throw new ThrottlingException("The incoming request exceeded the limit of " + properties.getCountLimit() + " requests in " + properties.getTimeLimit().toString());
        }
    }

    private boolean limitExceed(String remoteAddress) {
        return !service.check(remoteAddress, properties.getTimeLimit().toMillis(), properties.getCountLimit());
    }
}
