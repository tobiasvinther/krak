package kea.krak.security;

import kea.krak.entities.BaseUser;
import kea.krak.security.UserDetailsImp;
import kea.krak.security.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    UserRepository userRepository;

    public UserDetailsServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<BaseUser> optionalUser = userRepository.findByUsername(username);
        return optionalUser.map(UserDetailsImp::new).orElseThrow(() -> new BadCredentialsException(""));
    }

}
