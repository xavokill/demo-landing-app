package mx.com.backend.prospectos.integration;
import org.junit.jupiter.api.Test;
import mx.com.backend.prospectos.base.TestBase;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TestService extends TestBase {

    @Test
    void contextLoads() {
        assertTrue(template.getForEntity("/actuator/health", String.class).getStatusCode().is2xxSuccessful());
    }

    @Test
    void test_is_server_up() {
        assertTrue(template.getForEntity("/actuator/health", String.class).getStatusCode().is2xxSuccessful());

    }

}
