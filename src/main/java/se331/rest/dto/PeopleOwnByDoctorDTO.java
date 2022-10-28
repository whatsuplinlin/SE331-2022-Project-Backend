package se331.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeopleOwnByDoctorDTO {
    Long id;
    String name;
    String surname;
    String image;
    String age;
    String gender;
    String hometown;
    String status;
    List<CommentDTO> commentList;
    List<VaccineDTO> vaccineList;
}
