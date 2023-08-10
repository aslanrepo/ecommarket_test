package ton.telegrambots.ecommarket.testproject.service;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RequestLimitCheckServiceImpl implements RequestLimitCheckService {

    private final Map<String, RequestData> table;

    public RequestLimitCheckServiceImpl() {
        table = new ConcurrentHashMap<>();
    }

    public RequestLimitCheckServiceImpl(Map<String, RequestData> table) {
        this.table = table;
    }

    @Override
    public boolean check(String ipAddress, Long timeLimit, Integer countLimit) {
        RequestData result = table.compute(ipAddress, (ip, requestData) -> {
            if (isNewIp(requestData) || isElapsed(requestData, timeLimit)) {
                return new RequestData(Instant.now(), 1);
            } else {
                return new RequestData(requestData.initialTime(), requestData.count() + 1);
            }
        });
        return !isRequestLimitExceed(result, countLimit);
    }

    private boolean isRequestLimitExceed(RequestData requestData, Integer countLimit) {
        return requestData.count() > countLimit;
    }

    private boolean isElapsed(RequestData v, Long timeLimitMills) {
        return Instant.now().toEpochMilli() - v.initialTime().toEpochMilli() >= timeLimitMills;
    }

    private boolean isNewIp(RequestData v) {
        return v == null;
    }
}
