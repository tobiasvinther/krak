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

    /*
    @ManyToOne
    private Person person;*/

    public Hobby() {}

    public Hobby(String name, String description) {
        this.name = name;
        this.description = description;

    }
    public Hobby(HobbyRequest body) {
        this.name = body.getName();
        this.description = body.getDescription();
    }

    @Column(length = 40)
    String name;

    @Column(length = 60)
    String description;
}
