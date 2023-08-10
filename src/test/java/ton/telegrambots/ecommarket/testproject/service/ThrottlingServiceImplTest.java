package ton.telegrambots.ecommarket.testproject.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ton.telegrambots.ecommarket.testproject.config.ThrottlingProperties;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ThrottlingServiceImplTest {

    private ThrottlingProperties properties;
    @Mock
    private RequestLimitCheckService service;
    private ThrottlingServiceImpl target;

    @BeforeEach()
    void setUp() {
        properties = new ThrottlingProperties();
        properties.setCountLimit(0);
        properties.setTimeLimit(Duration.ZERO);
        target = new ThrottlingServiceImpl(properties, service);

    }

    @Test
    public void shouldPass() {
        doReturn(true).when(service).check(any(), any(), any());

        assertDoesNotThrow(() -> {
            target.enforce("B32bc:e:a5");
        });
    }

    @Test
    public void shouldThrow() {
        doReturn(false).when(service).check(any(), any(), any());

        assertThrows(ThrottlingException.class, () -> {
            target.enforce("B32bc:e:a5");
        });
    }

}