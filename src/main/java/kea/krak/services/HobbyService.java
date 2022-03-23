package kea.krak.services;

import kea.krak.dtos.HobbyRequest;
import kea.krak.dtos.HobbyResponse;
import kea.krak.entities.Hobby;
import kea.krak.repositories.HobbyRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class HobbyService {

    //acces to HobbeRepo
    HobbyRepository hobbyRepository;

    public HobbyService(HobbyRepository hobbyRepository) {
        this.hobbyRepository = hobbyRepository;
    }
    //get multiple hobbys
    public List<HobbyResponse> getHobbys() {
        List<Hobby> hobbys = hobbyRepository.findAll();
        return HobbyResponse.getHobbysFromEntities(hobbys);
    }
    //get a single hobby
    public HobbyResponse getHobby(int id,boolean all) throws Exception {
        Hobby hobby = hobbyRepository.findById(id).orElseThrow(()-> new Exception("not found"));
        return new HobbyResponse(hobby, false);
    }
}
