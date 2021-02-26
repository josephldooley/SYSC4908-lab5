package joe.app;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * model.BuddyInfo stores the name and phone number of the individual
 * @author Joseph Dooley
 * @version 2021/01/15
 */
@Entity
public class BuddyInfo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phoneNum;
    private String address;

    /**
     * constructor for buddy info taking a name and phone number.\
     */

    public BuddyInfo() {
    }

    /**
     * constructor for buddy info taking a name and phone number.
     * @param name String name of individual
     * @param phoneNum String phone number of individual
     */
    public BuddyInfo(String name, String phoneNum, String address) {
        this.name = name;
        this.phoneNum=phoneNum;
        this.address=address;
    }

    /**
     * Gets Id of buddy
     * @return Integer Id of buddy info
     */
    public Long getId() {
        return id;
    }

    /**
     * Get the name of the Buddy
     * @return String name of Buddy
     */
    public String getName() {
        return name;
    }

    /**
     * Get the phone number of the Buddy
     * @return String phone number of Buddy
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * Set name of buddy
     * @param name String name of buddy
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set phone number of buddy
     * @param phoneNum String phone number of buddy
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    /**
     * get address of buddy
     * @return String address of buddy
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set address of buddy
     * @param address String address of buddy
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * converts buddy info into string containing name and phone number
     * @return String name and phone number
     */
    @Override
    public String toString() {
        return "id: "+id+" | name: "+ name+" | phone number: "+phoneNum + " | address: "+address;
    }
}
