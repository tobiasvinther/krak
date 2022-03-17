package kea.krak.api;


import kea.krak.services.HobbyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/hobby")
public class HobbyController {
    HobbyService hobbyService;

    public HobbyController(HobbyService hobbyService) {
        this.hobbyService = hobbyService;
    }
}
