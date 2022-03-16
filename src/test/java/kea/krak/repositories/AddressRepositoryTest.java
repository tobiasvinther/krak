package kea.krak.repositories;

import kea.krak.entities.Address;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AddressRepositoryTest {

    @Autowired
    AddressRepository addressRepository;

    @BeforeEach
    void setUp() {
        Address a1 = new Address("Main Street", "Farpoint Station", 1);
        Address a2 = new Address("Academy Street", "San Fransisco", 2);
        addressRepository.save(a1);
        addressRepository.save(a2);
    }

    @AfterEach
    void tearDown() {
        addressRepository.deleteAll();
    }

    @Test
    public void testCount() {
        assertEquals(2, addressRepository.count());
    }

}
