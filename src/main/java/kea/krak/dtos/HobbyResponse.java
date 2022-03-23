package kea.krak.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import kea.krak.entities.Hobby;
import kea.krak.entities.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HobbyResponse {

    int id;
    String hobbyName;
    String hobbyDescription;

    String name;
    private Set<Person> personsList;
    //String description;

    public HobbyResponse(Hobby hobby) {
        this.name = hobby.getHobbyName();
        //this.description = hobby.getDescription();
        this.id = hobby.getId();
        this.personsList = hobby.getPersonList();
    }

    /*
    public static List<HobbyResponse> getHobbysFromEntities(List<Hobby> hobbys){
        return hobbys.stream().map(hobby-> new HobbyResponse(hobby, false)).collect(Collectors.toList());
        //We will do this together
    }*/
}
