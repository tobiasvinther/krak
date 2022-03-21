package kea.krak.repositories;
import kea.krak.entities.Hobby;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class HobbyRepositoryTest {

    @Autowired
    HobbyRepository hobbyRepository;

    @BeforeEach
    void setUp(){
        Hobby h1 = new Hobby("Tennis", "Its great");
        Hobby h2 = new Hobby("Fodbold", "Its great too");
        Hobby h3 = new Hobby("F1", "Its also great");
        hobbyRepository.save(h1);
        hobbyRepository.save(h2);
        hobbyRepository.save(h3);
    }
    @AfterEach
    void tearDown() {
        hobbyRepository.deleteAll();
    }
    @Test
    public void testCount() {
        assertEquals(3, hobbyRepository.count());
    }
}