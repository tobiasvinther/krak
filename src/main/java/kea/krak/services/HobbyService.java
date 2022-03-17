package kea.krak.services;

import kea.krak.repositories.HobbyRepository;
import org.springframework.stereotype.Service;

@Service
public class HobbyService {

    HobbyRepository hobbyRepository;

    public HobbyService(HobbyRepository hobbyRepository) {
        this.hobbyRepository = hobbyRepository;
    }
}
