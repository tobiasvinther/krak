package kea.krak.configuration;

import kea.krak.dtos.HobbyResponse;
import kea.krak.entities.Address;
import kea.krak.entities.Hobby;
import kea.krak.entities.Person;
import kea.krak.repositories.AddressRepository;
import kea.krak.repositories.HobbyRepository;
import kea.krak.repositories.PersonRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

@Controller
@Profile("!test")
public class MakeTestDataTobias implements ApplicationRunner {

    private PersonRepository personRepository;
    private AddressRepository addressRepository;
    private HobbyRepository hobbyRepository;

    Hobby h1;
    Hobby h2;
    Hobby h3;

    Address a1;
    Address a2;
    Person p1;
    Person p2;

    public MakeTestDataTobias(PersonRepository personRepository, AddressRepository addressRepository, HobbyRepository hobbyRepository) {
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
        this.hobbyRepository = hobbyRepository;
    }

    public void creatingHobbys() {
        h1 = new Hobby("Tennis", "Tennis er en fed sport");
        h2 = new Hobby("Fodbold", "Fodbold er også en fed sportsgren");
        h3 = new Hobby("F1", "Race Race Race!");
        hobbyRepository.save(h1);
        hobbyRepository.save(h2);
        hobbyRepository.save(h3);
    }

    public void createDefaultAddresses() {
        a1 = new Address("Main Street", "Farpoint Station", 1);
        a2 = new Address("Academy Street", "San Francisco", 2);
        addressRepository.save(a1);
        addressRepository.save(a2);
    }

    public void createDefaultPersons() {
        p1 = new Person("user1", "Jean-Luc","Picard", "Test123", "p1@email.com", 102030, a1);
        p1.addHobby(h1);
        p1.addHobby(h2);
        p2 = new Person("user2","Geordi", "LaForge", "Test123",  "p2@email.com" , 102030, a1);
        p2.addHobby(h1);
        personRepository.save(p1);
        personRepository.save(p2);
    }

    @Override
    public void run(ApplicationArguments args) {
        hobbyRepository.deleteAll();
        personRepository.deleteAll();
        addressRepository.deleteAll();
        creatingHobbys();
        createDefaultAddresses();
        createDefaultPersons();
     }

}

