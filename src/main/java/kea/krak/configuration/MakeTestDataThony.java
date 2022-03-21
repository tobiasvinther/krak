package kea.krak.configuration;

import kea.krak.entities.Hobby;
import kea.krak.repositories.HobbyRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@Profile("!test")
public class MakeTestDataThony implements ApplicationRunner {

    private HobbyRepository hobbyRepository;

    Hobby h1;
    Hobby h2;
    Hobby h3;

    public MakeTestDataThony(HobbyRepository hobbyRepository) {
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
    public void run(ApplicationArguments args) {
        hobbyRepository.deleteAll();
        creatingHobbys();
    }
}


