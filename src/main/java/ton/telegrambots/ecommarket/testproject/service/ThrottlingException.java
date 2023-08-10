package ton.telegrambots.ecommarket.testproject.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class ThrottlingException extends RuntimeException {
    public ThrottlingException(String s) {
        super(s);
    }
}
