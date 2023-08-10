package ton.telegrambots.ecommarket.testproject.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.assertj.core.api.Assertions.assertThat;

class RequestLimitCheckServiceImplTest {

    private Map<String, RequestData> table;
    private RequestLimitCheckService target;

    @BeforeEach
    public void setUp() {
        table = new HashMap<>();
        target = new RequestLimitCheckServiceImpl(table);
    }

    @Test
    public void shouldRestrictSecondRequest() {
        assertThat(target.check("B32bc:e:a5", 100_000L, 1)).isTrue();
        assertThat(target.check("B32bc:e:a5", 100_000L, 1)).isFalse();
    }

    @Test
    public void shouldPassSecondRequest() throws InterruptedException {
        assertThat(target.check("B32bc:e:a5", 1L, 1)).isTrue();
        Thread.sleep(5);
        assertThat(target.check("B32bc:e:a5", 1L, 1)).isTrue();
    }

    @Test
    public void shouldPassNewLimitRequest() throws InterruptedException {
        assertThat(target.check("B32bc:e:a5", 100_000L, 1)).isTrue();
        Thread.sleep(5);
        assertThat(target.check("B32bc:e:a5", 1L, 1)).isTrue();
    }
}