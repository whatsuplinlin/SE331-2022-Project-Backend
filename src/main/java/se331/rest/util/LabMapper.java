package se331.rest.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import se331.rest.dto.*;
import se331.rest.entity.*;
import se331.rest.security.entity.User;
import se331.rest.security.entity.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(imports = Collectors.class)
public interface LabMapper {
    LabMapper INSTANCE = Mappers.getMapper(LabMapper.class);
     PeopleDTO getPeopleDto(People people);
     CommentDTO getCommentDto(Comment comment);

     VaccineDTO getVaccineDto(Vaccine vaccine);
   List<DoctorDTO> getDoctorDto(List<Doctor> doctors);
   List<CommentDTO> getCommentDto(List<Comment> comments);
    List<PeopleDTO> getPeopleDto(List<People> people);
    UserDTO getUserDTO(User user);
    DoctorDTO getDoctorDto(Doctor doctor);
    List<UserDTO>getUserDto(List<User> user);
    AdminDTO getAdminDTO(Admin admin);

    @Mapping(target = "authorities", expression = "java(admin.getUser().getAuthorities().stream().map(auth -> auth.getName().name()).collect(Collectors.toList()))")
    AdminAuthDTO getAdminAuthDTO(Admin admin);

    @Mapping(target = "authorities", expression = "java(people.getUser().getAuthorities().stream().map(auth -> auth.getName().name()).collect(Collectors.toList()))")
    PeopleAuthDTO getPeopleAuthDTO(People people);

    @Mapping(target = "authorities", expression = "java(doctor.getUser().getAuthorities().stream().map(auth -> auth.getName().name()).collect(Collectors.toList()))")
    DoctorAuthDTO getDoctorAuthDTO(Doctor doctor);

}
