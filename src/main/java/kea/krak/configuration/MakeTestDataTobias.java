package kea.krak.configuration;

import kea.krak.entities.Address;
import kea.krak.entities.Person;
import kea.krak.repositories.AddressRepository;
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

    Address a1;
    Address a2;
    Person p1;
    Person p2;

    public MakeTestDataTobias(PersonRepository personRepository, AddressRepository addressRepository) {
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
    }

    public void createDefaultAddresses() {
        a1 = new Address("Main Street", "Farpoint Station", 1);
        a2 = new Address("Academy Street", "San Francisco", 2);
        addressRepository.save(a1);
        addressRepository.save(a2);
    }

    public void createDefaultPersons() {
        p1 = new Person("p1@email.com", "Jean-Luc", "Picard", 102030, a1);
        p2 = new Person("p2@email.com", "Geordi", "LaForge", 102030, a1);
        personRepository.save(p1);
        personRepository.save(p2);
    }

    @Override
    public void run(ApplicationArguments args) {
        personRepository.deleteAll();
        addressRepository.deleteAll();
        createDefaultAddresses();
        createDefaultPersons();
     }

}
