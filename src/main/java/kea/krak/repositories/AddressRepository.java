package kea.krak.repositories;

import kea.krak.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository <Address, Integer> {
}
