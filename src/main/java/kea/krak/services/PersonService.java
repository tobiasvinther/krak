package kea.krak.services;

import kea.krak.dtos.PersonResponse;
import kea.krak.entities.Person;
import kea.krak.repositories.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonResponse convertPersonToPersonResponse(Person personToConvert) {
        return new PersonResponse(personToConvert);
    }

    /*
    public static List<PersonResponse> getPersonsFromEntities(List<Person> personList) {
        return personList.stream().map(person -> new PersonResponse(person)).collect(Collectors.toList());
    }
     */

    public List<PersonResponse> getAllPersons() {
        List<Person> personsAsEntities = personRepository.findAll();
        return personsAsEntities.stream().map(person -> new PersonResponse(person)).collect(Collectors.toList());
    }

    public PersonResponse getPersonByUsername(String username) {
        return convertPersonToPersonResponse(personRepository.getById(username));
    }

    public Person getPersonAsEntity(String username) {
        return personRepository.getById(username);
    }

}
