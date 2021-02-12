package joe.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Test addressBook class
 */
@SpringBootTest
public class AddressBookTest {

    private BuddyInfo b1= new BuddyInfo("joe","123-233-4122","45 home");
    private BuddyInfo b2= new BuddyInfo("jim","222-222-2222","53 street");
    private AddressBook address=new AddressBook();
    /**
     * test adding a buddy to the address book
     */
    @Test
    public void addBuddyTest() {
        address.addBuddy(b1);
        assertEquals(b1,address.getBuddy(0));
    }

    /**
     * test remoiving a buddy from the address book
     */
    @Test
    public void removeBuddyTest() {
        address.addBuddy(b2);
        address.removeBuddy(b2);
        assertNull(address.getBuddy(0));
    }

    @Test
    public void toStringTest() {
        address.addBuddy(b1);
        address.addBuddy(b2);
        String expected ="Address book contains: \n" + b1+ "\n" + b2 + "\n";
        assertEquals(expected,address.toString());
    }
}