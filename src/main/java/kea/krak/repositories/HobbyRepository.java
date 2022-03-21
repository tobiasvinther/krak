package kea.krak.repositories;

import kea.krak.entities.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbyRepository extends JpaRepository<Hobby, String> {

}
