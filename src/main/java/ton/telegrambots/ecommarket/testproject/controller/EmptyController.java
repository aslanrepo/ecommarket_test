package ton.telegrambots.ecommarket.testproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ton.telegrambots.ecommarket.testproject.aspect.Throttled;

@RestController
public class EmptyController {
    @Throttled
    @RequestMapping("/")
    public ResponseEntity<Void> test() {
        return ResponseEntity.status(200).build();
    }
}
