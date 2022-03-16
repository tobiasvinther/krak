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

    AddressRepository addressRepository;

    @BeforeEach
    void setUp() {
       //Address a1 = new Address("Main Street", "Farpoint Station", 1);
        //addressRepository.save(a1);
        personRepository.save(new Person("p1@email.com", "Jean-Luc", "Picard", 102030, (new Address("Main Street", "Farpoint Station", 1))));
    }

    @Test
    public void testCount() {
        assertEquals(1, personRepository.count());
    }
}