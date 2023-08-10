package ton.telegrambots.ecommarket.testproject.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RequestLimitCheckServiceImplConcurrentTest {

    private Map<String, RequestData> table;
    private RequestLimitCheckService target;

    @BeforeEach
    public void setUp() {
        table = new ConcurrentHashMap<>();
        target = new RequestLimitCheckServiceImpl(table);
    }
    @Test
    public void shouldKeepCounterConcurrently() throws InterruptedException {
        var numberOfThreads = 10;
        var numberOfAttempts = 10;
        var numberOfAddresses = 5;
        var service = Executors.newFixedThreadPool(numberOfThreads);
        Stream.generate(() -> UUID.randomUUID().toString())
                .limit(numberOfAddresses)
                .forEach(ip -> {
                    for (int i = 0; i < numberOfAttempts; i++) {
                        service.execute(() -> {
                            target.check(ip, 100_000L, 100_000);
                        });
                    }
                });

        service.shutdown();
        assertThat(service.awaitTermination(3, TimeUnit.SECONDS)).isTrue();
        assertThat(table.size()).isEqualTo(numberOfAddresses);
        assertThat(table.values().stream().noneMatch(req-> req.count() != numberOfAttempts)).isTrue();
    }
}