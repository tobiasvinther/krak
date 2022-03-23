package kea.krak.entities;

import kea.krak.dtos.PersonRequest;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Person extends BaseUser {

    private String firstName;
    private String lastName;
    private int phoneNumber;

    @ManyToOne
    private Address address;

    @ManyToMany
    private Set<Hobby> hobbies = new HashSet<>();

    public Person() {
    }

    public Person(String username, String email, String password, String firstName, String lastName, int phoneNumber, Address address) {
        super(username, email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        address.addPersonToAddress(this); //add person to address
    }

    public void addHobby(Hobby hobby) {
        hobbies.add(hobby);
        hobby.addPerson(this);
    }

}
