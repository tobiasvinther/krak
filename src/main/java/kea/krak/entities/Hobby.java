package kea.krak.entities;

import kea.krak.dtos.HobbyRequest;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Hobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    public Hobby() {}

    public Hobby(String name, String description) {
        this.name = name;
        this.description = description;

    }
    public Hobby(HobbyRequest body) {
        this.name = body.getName();
        this.description = body.getDescription();
    }
}
