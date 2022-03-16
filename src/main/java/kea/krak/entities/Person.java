package kea.krak.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Person {

    @Id
    private String email;

    private String firstName;
    private String lastName;
    private int phoneNumber;

    @ManyToOne
    private Address address;

    public Person() {
    }

    public Person(String email, String firstName, String lastName, int phoneNumber, Address address) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        address.addPersonToAddress(this); //add person to address
    }
}
