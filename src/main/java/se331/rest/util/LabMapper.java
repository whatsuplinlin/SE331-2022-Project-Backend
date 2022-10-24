package se331.rest.util;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import se331.rest.entity.Comment;
import se331.rest.entity.People;
import se331.rest.dto.CommentDTO;
import se331.rest.dto.PeopleDTO;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(imports = Collectors.class)
public interface LabMapper {
    LabMapper INSTANCE = Mappers.getMapper(LabMapper.class);
     PeopleDTO getPeopleDto(People people);
     CommentDTO getCommentDto(Comment comment);
   List<CommentDTO> getCommentDto(List<Comment> comments);
    List<PeopleDTO> getPeopleDto(List<People> people);
}
