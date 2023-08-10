package ton.telegrambots.ecommarket.testproject.service;

public interface ThrottlingService {
    void enforce(String remoteAddress);
}
