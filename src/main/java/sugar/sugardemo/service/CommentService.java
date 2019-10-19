package sugar.sugardemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sugar.sugardemo.domain.Comment;
import sugar.sugardemo.domain.User;
import sugar.sugardemo.repo.CommentRepository;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment create(Comment comment, User user) {
        comment.setAuthor(user);
        commentRepository.save(comment);
        return comment;
    }
}
