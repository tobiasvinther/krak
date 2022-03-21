package kea.krak.repositories;

import kea.krak.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonRepository extends JpaRepository<Person, String> {

    @Query("select (count(person) > 0) from Person person where person.email = :email")
    boolean emailExist(String email);

}
