package kea.krak.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import kea.krak.entities.Hobby;
import kea.krak.entities.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonResponse {
    //private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String city;
    private Set<Hobby> hobbies;

    //create a PersonResponse object by providing a Person object
    public PersonResponse(Person person) {
        this.username = person.getUsername();
        //this.email = person.getEmail();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.city = person.getAddress().getCity();
        this.hobbies = person.getHobbies();
    }

}
