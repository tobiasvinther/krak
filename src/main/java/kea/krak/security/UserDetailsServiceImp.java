package kea.krak.security;

import kea.krak.entities.Person;
import kea.krak.repositories.PersonRepository;
import kea.krak.security.UserDetailsImp;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    PersonRepository personRepository;

    public UserDetailsServiceImp(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<Person> optionalUser = personRepository.findByUsername(username);
        return optionalUser.map(UserDetailsImp::new).orElseThrow(() -> new BadCredentialsException(""));
    }

}
