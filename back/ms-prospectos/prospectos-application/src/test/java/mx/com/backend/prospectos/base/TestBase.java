package mx.com.backend.prospectos.base;

import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import mx.com.backend.prospectos.Application;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class TestBase {

    @Autowired
    protected TestRestTemplate template;

    @BeforeEach
    void before() {
        //demo to show how to add custom header Globally for the http request in spring test template , like IV user header
        template.getRestTemplate().setInterceptors(Collections.singletonList((request, body, execution) -> {
            request.getHeaders()
                    .add("iv-user", "user");
            return execution.execute(request, body);
        }));
    }

}
