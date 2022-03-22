package kea.krak.entities;

import kea.krak.dtos.HobbyRequest;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Hobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(length = 40)
    String hobbyName;

    @Column(length = 60)
    String hobbyDescription;

    @OneToMany(mappedBy = "hobby")
    private Set<Person> personsAtHobby = new HashSet<>();

    /*
    @ManyToOne
    @JoinColumn(name = "hobby_id")
    private Hobby hobby; */

    /*
    @ManyToOne
    private Person person;*/

    public Hobby() {}

    public Hobby(String hobbyName, String hobbyDescription) {
        this.hobbyName = hobbyName;
        this.hobbyDescription = hobbyDescription;

    }
    public Hobby(HobbyRequest body) {
        this.hobbyName = body.getHobbyName();
        this.hobbyDescription = body.getHobbyDescription();
    }
}
