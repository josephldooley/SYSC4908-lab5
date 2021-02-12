package joe.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AddressController {

    private final AddressBookRepo addressRepo;
    private final BuddyRepo buddyRepo;

    AddressController(AddressBookRepo addressRepo, BuddyRepo buddyRepo){
        this.addressRepo=addressRepo;
        this.buddyRepo=buddyRepo;
    }

    @GetMapping("/addresspage")
    public String addresspage(Model model) {
        String output ="No AddressBooks";
        List<AddressBook> books=addressRepo.findAll();
        model.addAttribute("books", books);
        if(books.isEmpty()){
            model.addAttribute("msg","No addressbooks");
        }
        return "addresspage";
    }

    @GetMapping("/buddiespage/{id}")
    public String buddiespage(@PathVariable long id, Model model) {
        String output ="No AddressBook with id"+id;
        AddressBook addressBook = addressRepo.findById(id);
        if (addressBook!=null){
            output= addressBook.toString();
        }
        model.addAttribute("buddies", output);
        return "buddiespage";
    }

    @ResponseBody
    @PostMapping("/addressbook")
    AddressBook newAddressBook(){
        return addressRepo.save(new AddressBook());
    }

    @ResponseBody
    @GetMapping("/addressbooks")
    List<AddressBook> allBooks(){
        return addressRepo.findAll();
    }

    @ResponseBody
    @GetMapping("/buddies")
    List<BuddyInfo> allBuddies(){
        return buddyRepo.findAll();
    }

    @ResponseBody
    @PostMapping("/buddy")
    BuddyInfo newBuddy(@RequestParam(name = "bookId") long id, @RequestParam(name = "name") String name, @RequestParam(name = "phonenum") String phoneNum){
        AddressBook addressBook = addressRepo.findById(id);
        if (addressBook==null){
            return null;
        }
        BuddyInfo newBuddy = new BuddyInfo(name,phoneNum);
        addressBook.addBuddy(newBuddy);
        buddyRepo.save(newBuddy);
        addressRepo.save(addressBook);
        return newBuddy;
    }

    @ResponseBody
    @DeleteMapping("/buddy")
    void removeBuddy(@RequestParam(name = "bookId")long bookId, @RequestParam(name = "buddyId")long buddyId){
        AddressBook addressBook = addressRepo.findById(bookId);
        BuddyInfo buddy = buddyRepo.findById(buddyId);
        if(buddy == null || addressBook == null){
            return;
        }
        addressBook.removeBuddy(buddy);
        addressRepo.save(addressBook);
        buddyRepo.deleteById(buddyId);
    }
}
