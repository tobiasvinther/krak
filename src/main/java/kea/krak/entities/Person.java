package kea.krak.entities;

import kea.krak.security.UserWithPassword;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Entity
@Getter
@Setter
public class Person implements UserWithPassword {
    @Id
    private String username;

    @Email
    @Column(nullable = false, unique = true,length = 50)
    private String email;

    // 72 == Max length of a bcrypt encoded password
    // https://cheatsheetseries.owasp.org/cheatsheets/Password_Storage_Cheat_Sheet.html
    @Column(nullable = false, length = 72)
    private String password;

    boolean enabled;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('USER','ADMIN')")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="user_role")
    List<Role> roles = new ArrayList<>();

    private String firstName;
    private String lastName;
    private int phoneNumber;

    @ManyToOne
    private Address address;
    //ManyToOne @JoinColumn Private Hobby hobby indsat af Thony
    //@ManyToOne
    //@JoinColumn(name = "hobby_id")
    //private Hobby hobby;

    @ManyToMany
    private Set<Hobby> hobbies = new HashSet<>();

    public Person() {
    }

    public Person( String firstName, String lastName,String email, int phoneNumber, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        address.addPersonToAddress(this); //add person to address
    }

    public Person(String username, String firstName, String lastName, String password, String email, int phoneNumber, Address address) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = pwEncoder.encode(password);
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        address.addPersonToAddress(this); //add person to address
    }

    public void addHobby(Hobby hobby) {
        hobbies.add(hobby);
        hobby.addPerson(this);
    }

    public Person(String username,String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = pwEncoder.encode(password);
        this.enabled = true;
    }

    @Override
    public void setPassword(String password) {
        this.password = pwEncoder.encode(password);
    }
    @Override
    public List<Role> getRoles() {
        return roles;
    }
    @Override
    public void addRole(Role role){
        roles.add(role);
    }

}
