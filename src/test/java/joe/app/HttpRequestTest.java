package joe.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAddressBooks() {
        String e= this.restTemplate.getForObject("http://localhost:" + port + "/addressbooks",
                String.class);
        assertThat(e).contains("[]");
    }

    @Test
    public void getBuddies() {
        String e= this.restTemplate.getForObject("http://localhost:" + port + "/buddies",
                String.class);
        assertThat(e).contains("[]");
    }

}
