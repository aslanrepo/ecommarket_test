package ton.telegrambots.ecommarket.testproject.service;

public interface RequestLimitCheckService {
    boolean check(String ipAddress, Long timeLimit, Integer countLimit);
}
