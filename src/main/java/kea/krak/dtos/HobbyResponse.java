package kea.krak.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import kea.krak.entities.Hobby;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HobbyResponse {

    int id;
    String hobbyName;
    String hobbyDescription;

    public HobbyResponse(Hobby hobby) {
        this.hobbyName = hobby.getHobbyName();
        this.hobbyDescription = hobby.getHobbyDescription();
        this.id = hobby.getId();
    }

    /*
    public static List<HobbyResponse> getHobbysFromEntities(List<Hobby> hobbys){
        return hobbys.stream().map(hobby-> new HobbyResponse(hobby, false)).collect(Collectors.toList());
        //We will do this together
    }*/
}
