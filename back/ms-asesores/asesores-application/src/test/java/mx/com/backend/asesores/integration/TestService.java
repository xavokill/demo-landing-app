package mx.com.backend.asesores.integration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import mx.com.backend.asesores.Application;
import mx.com.backend.asesores.base.TestBase;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

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
