package kea.krak.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String street;
    private String city;
    private int houseNumber;

    @OneToMany(mappedBy = "address")
    private Set<Person> personsAtAddress = new HashSet<>();

    public Address() {
    }

    public Address(String street, String city, int houseNumber) {
        this.street = street;
        this.city = city;
        this.houseNumber = houseNumber;
        //this.personsAtAddress = personsAtAddress;
    }

    public void addPersonToAddress(Person personToAdd) {
        personsAtAddress.add(personToAdd);
    }

}
