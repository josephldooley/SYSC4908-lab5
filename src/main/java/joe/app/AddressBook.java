package joe.app;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Address book manages a list of buddyinfo and can print out its info
 * @author Joseph Dooley
 * @version 2021/01/15
 */
@Entity
public class AddressBook {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @OneToMany()
    private Collection<BuddyInfo> buddies;

    /**
     * constructor for address book
     */
    public AddressBook() {
        buddies=new ArrayList<>();
    }

    /**
     * adds buddyinfo to address book if its not null.
     * @param buddy model.BuddyInfo buddy to add
     */
    public void addBuddy(BuddyInfo buddy){
        if (buddy==null){
            System.out.println("can't add null buddy");
            return;
        }
        buddies.add(buddy);
    }

    /**
     * removes buddy from address book if not null and exist in the address book
     * @param buddy model.BuddyInfo buddy to remove
     */
    public void removeBuddy(BuddyInfo buddy){
        if (buddy==null){
            System.out.println("can't remove null buddy");
            return;
        }

        if(!buddies.remove(buddy)){
            System.out.println("buddy not in address book");
        }
    }

    /**
     * gets buddy from address book at index if it exists, otherwise it returns null
     * @param index int index of buddy to get
     * @return model.BuddyInfo the buddy at the given index
     */
    public BuddyInfo getBuddy(int index){
        try{
            return ((ArrayList<BuddyInfo>)buddies).get(index);
        }catch(Exception e) {
            System.out.println("must be in between 0 and size-1 of the addressbook");
            return null;
        }
    }

    /**
     * Get id of Address book
     * @return Long id of address book
     */

    public Long getId() {
        return id;
    }

    /**
     * Get Address book list
     * @return Collection<BuddyInfo> collection of buddyinfo
     */
    public Collection<BuddyInfo> getAddressBook() {
        return buddies;
    }

    /**
     * Set address book list
     * @param address address to set
     */
    public void setAddressBook(Collection<BuddyInfo> address) {
        this.buddies = address;
    }

    public int length(){
        return buddies.size();
    }

    /**
     * converts address book into list of buddies names and phone numbers
     * @return String of buddies names and phone numbers
     */
    @Override
    public String toString() {
        StringBuilder addressBookString = new StringBuilder();
        addressBookString.append("Address book contains: \n");
        if(buddies.isEmpty()){
            addressBookString.append("No buddies here");
        }else {
            for (BuddyInfo buddy : buddies) {
                addressBookString.append(buddy + "\n");
            }
        }
        return addressBookString.toString();
    }
}
