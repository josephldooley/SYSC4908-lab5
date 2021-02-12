package joe.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Junit 4 to test model.BuddyInfo
 */
@SpringBootTest
public class BuddyInfoTest {

    private final String name ="joe";
    private final String phoneNumber ="1231231233";
    private final String address ="54 home";
    private BuddyInfo buddy=new BuddyInfo(name,phoneNumber,address);

    /**
     * test getName Method
     */
    @Test
    public void getNameTest() {
        String actual =buddy.getName();
        assertEquals(name,actual);
    }

    /**
     * test getPhoneNum Method
     */
    @Test
    public void getPhoneNumberTest() {
        String actual =buddy.getPhoneNum();
        assertEquals(phoneNumber,actual);
    }

    /**
     * test getAddress Method
     */
    @Test
    public void getAddressTest() {
        String actual =buddy.getAddress();
        assertEquals(address,actual);
    }

    /**
     * test toString method
     */
    @Test
    public void toStringTest() {
        String actual = buddy.toString();
        String expected= "id: null | name: "+ name+" | phone number: "+phoneNumber+" | address: "+address;
        assertEquals(expected,actual);
    }
}