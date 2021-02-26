package joe.app;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @GetMapping("/")
    public String addresspage(Model model) {
        String output ="No AddressBooks";
        List<AddressBook> books=addressRepo.findAll();
        model.addAttribute("books", books);
        if(books.isEmpty()){
            model.addAttribute("msg","No addressbooks");
        }

        model.addAttribute("buddyinfo", new BuddyInfoForm());
        return "addresspage";
    }

    @GetMapping("/buddiespage/{id}")
    public String buddiespage(@PathVariable long id, Model model) {
        String output ="No Buddies in AddressBook with id "+id;
        AddressBook addressBook = addressRepo.findById(id);
        if (addressBook!=null){
            output= addressBook.toString();
        }
        model.addAttribute("buddies", output);
        return "buddiespage";
    }

    @PostMapping("/addressview")
    public String addressview(Model model) {
        newAddressBook();
        List<AddressBook> books=addressRepo.findAll();
        model.addAttribute("books", books);
        model.addAttribute("buddyinfo",new BuddyInfoForm());
        return "addresspage";
    }

    @PostMapping("/buddyview")
    public String buddyview(@ModelAttribute("buddyinfo") BuddyInfoForm buddyinfo, Model model) {
        if (!(buddyinfo.getBookId()==null || buddyinfo.getName()==null || buddyinfo.getPhoneNum()==null  || buddyinfo.getAddress()==null )){
            newBuddy(buddyinfo.getBookId(),buddyinfo.getName(),buddyinfo.getPhoneNum(),buddyinfo.getAddress());
        }
        List<AddressBook> books=addressRepo.findAll();
        model.addAttribute("books", books);
        model.addAttribute("buddyinfo",new BuddyInfoForm());
        return "addresspage";
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
    BuddyInfo newBuddy(@RequestParam(name = "bookId") long id, @RequestParam(name = "name") String name, @RequestParam(name = "phonenum") String phoneNum, @RequestParam(name = "address") String address){
        AddressBook addressBook = addressRepo.findById(id);
        if (addressBook==null){
            return null;
        }
        BuddyInfo newBuddy = new BuddyInfo(name,phoneNum,address);
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
