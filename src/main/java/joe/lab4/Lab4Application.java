package joe.lab4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Lab4Application {

	private static final Logger log = LoggerFactory.getLogger(Lab4Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Lab4Application.class, args);
	}
/*
	@Bean
	public CommandLineRunner demo(BuddyRepo repository) {
		return (args) -> {
			// save a few buddyinfo
			repository.save(new BuddyInfo("Jack", "123-123-2345"));
			repository.save(new BuddyInfo("Chloe", "342-342-1111"));
			repository.save(new BuddyInfo("Kim", "465-563-5634"));
			repository.save(new BuddyInfo("David", "888-777-6666"));
			repository.save(new BuddyInfo("Michelle", "555-444-3334"));

			// fetch all buddyinfo
			log.info("buddyinfo found with findAll():");
			log.info("-------------------------------");
			log.info(repository.findAll().getClass().toString());
			for (BuddyInfo buddy : repository.findAll()) {
				log.info(buddy.toString());
			}
			log.info("");

			// fetch an individual buddyinfo by ID
			BuddyInfo customer = repository.findById(1L);
			log.info("buddyinfo found with findById(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			// fetch buddyinfo by name
			log.info("buddyinfo found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByName("David").forEach(bauer -> {
				log.info(bauer.toString());
			});
			log.info("");
		};
	}*/
}
