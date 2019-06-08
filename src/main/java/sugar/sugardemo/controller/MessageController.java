package sugar.sugardemo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import sugar.sugardemo.domain.Message;
import sugar.sugardemo.domain.Views;
import sugar.sugardemo.dto.EventType;
import sugar.sugardemo.dto.ObjectType;
import sugar.sugardemo.repo.MessageRepository;
import sugar.sugardemo.util.WsSender;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiConsumer;

@RestController
@RequestMapping("message")
public class MessageController {

    private final MessageRepository messageRepository;
    private final BiConsumer<EventType, Message> wsSender;

    public MessageController(MessageRepository messageRepository, WsSender wsSender) {
        this.messageRepository = messageRepository;
        this.wsSender = wsSender.getSender(ObjectType.MESSAGE, Views.Id.class);
    }


    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Message> list() {
        return messageRepository.findAll();
    }

    @GetMapping("{id}")
    public Message getOne(@PathVariable("id") Message message) {
        return message;
    }

    @PostMapping
    public Message create(@RequestBody Message message) {
        message.setCreationDate(LocalDateTime.now());
        Message create = messageRepository.save(message);

        wsSender.accept(EventType.CREATE, create);

        return create;
    }

    @PutMapping("{id}")
    public Message update(@PathVariable("id") Message messageFromDb,
                          @RequestBody Message message) {
        BeanUtils.copyProperties(message, messageFromDb, "id");

        Message update = messageRepository.save(message);

        wsSender.accept(EventType.UPDATE, update);

        return messageRepository.save(messageFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message) {
        Message update = messageRepository.save(message);

        wsSender.accept(EventType.REMOVE, update);

        messageRepository.delete(message);
    }

//    @MessageMapping("/changeMessage")
//    @SendTo("/topic/activity")
//    public Message change(Message message){
//        return messageRepository.save(message);
//    }
}