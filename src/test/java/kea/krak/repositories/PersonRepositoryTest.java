package kea.krak.repositories;

import kea.krak.entities.Address;
import kea.krak.entities.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    AddressRepository addressRepository;

    private Person p1;

    @BeforeEach
    void setUp() {
        Address a1 = new Address("Main Street", "Farpoint Station", 1);
        addressRepository.save(a1);
        //personRepository.save(new Person("p1@email.com", "Jean-Luc", "Picard", 102030, a1));
        p1 = new Person("user1", "p1@email.com", "123", "Jean-Luc", "Picard", 102030, a1);
        personRepository.save(p1);
    }

    @AfterEach
    void tearDown() {
        personRepository.deleteAll();
        addressRepository.deleteAll();
    }

    @Test
    public void testCount() {
        assertEquals(1, personRepository.count());
    }

    @Test
    public void testDelete() {
        personRepository.delete(personRepository.getById("p1@email.com"));
        assertEquals(0, personRepository.count());
    }

    //testing if a person is properly put in the address list of residents
    @Test
    public void testPersonInAddress() {
        assertTrue(addressRepository.getById(1).getPersonsAtAddress().contains(p1));
    }

}