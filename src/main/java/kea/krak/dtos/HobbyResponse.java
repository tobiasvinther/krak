package kea.krak.dtos;

import kea.krak.entities.Hobby;

import java.util.List;
import java.util.stream.Collectors;

public class HobbyResponse {

    int id;
    //Change CarBrand into a String if you are not using an enum for this field
    String name;
    String description;

    public HobbyResponse(Hobby hobby, boolean b) {
        this.name = hobby.getName();
        this.description = hobby.getDescription();
        this.id = hobby.getId();
    }

    public static List<HobbyResponse> getHobbysFromEntities(List<Hobby> hobbys){
        return hobbys.stream().map(hobby-> new HobbyResponse(hobby, false)).collect(Collectors.toList());
        //We will do this together
    }
}
