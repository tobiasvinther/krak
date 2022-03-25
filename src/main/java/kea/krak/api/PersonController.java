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

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
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


    @RolesAllowed("USER")
    @GetMapping("/authenticatedUser")
    public PersonResponse getMembersFromUsername(Principal principal) {
        return (personService.getPersonByUsername(principal.getName()));
    }


/*
    @RolesAllowed("USER")
    @GetMapping("/{username}")
    public PersonResponse getMembersFromUsername(@PathVariable String username) {
        return (personService.getPersonByUsername(username));
    }
*/

    @GetMapping("/{username}/get-hobbies")
    public Set<Hobby> getPersonHobbies(@PathVariable String username) {
        return personService.getPersonAsEntity(username).getHobbies();
    }

/*
    @PatchMapping("/{username}/add-hobby")
    public ResponseEntity<HobbyResponse> addHobby(@RequestBody HobbyRequest hobbyRequest, @PathVariable String username) throws Exception {
        personService.getPersonAsEntity(username).addHobby(hobbyRepository.getById(hobbyRequest.getId()));
        HobbyResponse hobbyResponse = hobbyService.getHobby(hobbyRequest.getId(), false);
        return ResponseEntity.ok(hobbyResponse);
    }
*/

    @PatchMapping("/{username}/add-hobby")
    public void addHobby(@RequestBody HobbyRequest hobbyRequest, @PathVariable String username) {
        personService.getPersonAsEntity(username).addHobby(hobbyRepository.getById(hobbyRequest.getId()));
    }




}
