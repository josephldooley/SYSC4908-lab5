package joe.app;

import org.junit.jupiter.api.BeforeEach;
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
    public void testAllEndpoints(){

        //create addressbook
        String e= this.restTemplate.postForObject("http://localhost:" + port + "/addressbook",null,
                String.class);
        assertThat(e).contains("{\"id\":1,\"addressBook\":[]}");

        //create buddyinfo
        e= this.restTemplate.postForObject("http://localhost:" + port + "/buddy?bookId=1&name=joee&phonenum=123-123-1232&address=66drive",null,
                String.class);
        assertThat(e).contains("{\"id\":1,\"name\":\"joee\",\"phoneNum\":\"123-123-1232\",\"address\":\"66drive\"}");

        //get addressbook
        e= this.restTemplate.getForObject("http://localhost:" + port + "/addressbooks",
                String.class);
        assertThat(e).contains("[{\"id\":1,\"addressBook\":[{\"id\":1,\"name\":\"joee\",\"phoneNum\":\"123-123-1232\",\"address\":\"66drive\"}]}]");

        //get buddyInfo
        e= this.restTemplate.getForObject("http://localhost:" + port + "/buddies",
                String.class);
        assertThat(e).contains("{\"id\":1,\"name\":\"joee\",\"phoneNum\":\"123-123-1232\",\"address\":\"66drive\"}");

        //delete buddy
        this.restTemplate.delete("http://localhost:" + port + "/buddy?bookId=1&buddyId=1");
        e= this.restTemplate.getForObject("http://localhost:" + port + "/addressbooks",
                String.class);
        assertThat(e).contains("[{\"id\":1,\"addressBook\":[]}]");
        e= this.restTemplate.getForObject("http://localhost:" + port + "/buddies",
                String.class);
        assertThat(e).contains("[]");
    }
}
