package sugar.sugardemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sugar.sugardemo.domain.Comment;
import sugar.sugardemo.domain.User;
import sugar.sugardemo.domain.Views;
import sugar.sugardemo.dto.EventType;
import sugar.sugardemo.dto.ObjectType;
import sugar.sugardemo.repo.CommentRepository;
import sugar.sugardemo.util.WsSender;

import java.util.function.BiConsumer;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
  private final BiConsumer<EventType, Comment> wsSender;

    @Autowired
    public CommentService(CommentRepository commentRepository, WsSender wsSender) {
        this.commentRepository = commentRepository;
      this.wsSender = wsSender.getSender(ObjectType.COMMENT, Views.FullComment.class);
    }

    public Comment create(Comment comment, User user) {
        comment.setAuthor(user);
      Comment commentFromDB = commentRepository.save(comment);

      wsSender.accept(EventType.CREATE, commentFromDB);

      return commentFromDB;
    }
}
