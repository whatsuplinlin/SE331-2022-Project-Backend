package se331.rest.service;

import se331.rest.entity.Comment;
import se331.rest.entity.People;

import java.util.List;

public interface CommentService {
    Comment save(Comment comment);
    List<Comment> getAllComment();
}
