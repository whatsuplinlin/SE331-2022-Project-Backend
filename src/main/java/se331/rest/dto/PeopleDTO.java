package se331.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import se331.rest.security.entity.Authority;
import se331.rest.security.entity.User;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeopleDTO {
    Long id;
    String name;
    String surname;
    String image;
    Integer age;
    String gender;
    String hometown;
    String status;
    List<CommentDTO> commentList;
    List<VaccineDTO> vaccineList;
}
