package kea.krak.api;


import kea.krak.dtos.HobbyResponse;
import kea.krak.entities.Hobby;
import kea.krak.services.HobbyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/hobby")
public class HobbyController {
    HobbyService hobbyService;

    public HobbyController(HobbyService hobbyService) {
        this.hobbyService = hobbyService;
    }
    @GetMapping
    public List<HobbyResponse> getHobbys(){
        return hobbyService.getHobbys();
    }
    @GetMapping("/{id}")
    public HobbyResponse getHobbys(@PathVariable int id) throws Exception{
        return hobbyService.getHobby(id, false);
    }
}
