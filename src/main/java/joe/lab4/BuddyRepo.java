package joe.lab4;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BuddyRepo extends CrudRepository<BuddyInfo,Long> {

    List<BuddyInfo> findAll();
    
    List<BuddyInfo> findByName(String name);

    List<BuddyInfo> findByPhoneNum(String phoneNum);

    BuddyInfo findById(long id);

}
