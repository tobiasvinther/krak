package kea.krak.api;

import kea.krak.dtos.HobbyRequest;
import kea.krak.dtos.HobbyResponse;
import kea.krak.dtos.PersonResponse;
import kea.krak.entities.Hobby;
import kea.krak.repositories.HobbyRepository;
import kea.krak.services.HobbyService;
import kea.krak.services.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("api/persons")
public class PersonController {

    private PersonService personService;
    private HobbyRepository hobbyRepository;
    private HobbyService hobbyService;

    public PersonController(PersonService personService, HobbyRepository hobbyRepository, HobbyService hobbyService) {
        this.personService = personService;
        this.hobbyRepository = hobbyRepository;
        this.hobbyService = hobbyService;
    }

    /*
    @GetMapping
    public ResponseEntity<List<PersonResponse>> getAllPersons() {
        return ResponseEntity.ok(personService.getAllPersons());
    }
     */

    @GetMapping
    public List<PersonResponse> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/{username}")
    public PersonResponse getMembersFromUsername(@PathVariable String username) {
        return (personService.getPersonByUsername(username));
    }

    //todo: create a method in hobby service to convert hobby set to set of hobbyResponses
    @GetMapping("/{username}/get-hobbies")
    public Set<Hobby> getPersonHobbies(@PathVariable String username) {
        return personService.getPersonAsEntity(username).getHobbies();
    }


    @PostMapping("/{username}/add-hobby")
    public ResponseEntity<HobbyResponse> register(@RequestBody HobbyRequest hobbyRequest, @PathVariable String username) throws Exception {
        personService.getPersonAsEntity(username).addHobby(hobbyRepository.getById(hobbyRequest.getId()));
        HobbyResponse hobbyResponse = hobbyService.getHobby(hobbyRequest.getId(), false);
        System.out.println("HELLO THERE");
        return ResponseEntity.ok(hobbyResponse);
    }

    /*
    @PostMapping("/{username}/add-hobby")
    public void addHobby(@RequestBody HobbyRequest hobbyRequest, @PathVariable String username) {
        personService.getPersonAsEntity(username).addHobby(hobbyRepository.getById(hobbyRequest.getId()));
    }

     */


}
