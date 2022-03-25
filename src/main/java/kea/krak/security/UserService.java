package kea.krak.security;


import kea.krak.dtos.PersonRequest;
import kea.krak.dtos.PersonResponse;
import kea.krak.entities.Person;
import kea.krak.entities.Role;
import kea.krak.errors.Client4xxException;
import kea.krak.repositories.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    PersonRepository personRepository;

    public UserService( PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonResponse createUser(PersonRequest request){

        if(personRepository.existsByUsername(request.getUsername())){
            throw new Client4xxException("Username is taken");
        }
        if(personRepository.existsByEmail(request.getEmail())){
            throw new Client4xxException("Email is used by another user");
        }

        Person user = new Person(request.getUsername(),request.getFirstName(), request.getLastName(), request.getEmail(),
                request.getPassword());


        //All new users are by default given the role CUSTOMER. Comment out the lines below if this is not your required behaviour
        user.addRole(Role.USER);

        personRepository.save(user);
        List<String> roleNames = user.getRoles().stream().map(role -> role.toString()).collect(Collectors.toList());
        //No need to return a body since primary key is the provided userName
        return new PersonResponse(roleNames);

    }

}
