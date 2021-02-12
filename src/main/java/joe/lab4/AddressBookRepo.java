package joe.lab4;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressBookRepo extends CrudRepository<AddressBook, Long> {
    List<AddressBook> findAll();
    AddressBook findById(long id);
}
