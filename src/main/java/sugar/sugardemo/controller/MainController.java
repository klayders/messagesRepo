package sugar.sugardemo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sugar.sugardemo.domain.User;
import sugar.sugardemo.domain.Views;
import sugar.sugardemo.repo.MessageRepository;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {

    @Value("${spring.profiles.active}")
    private String profile;

    private final MessageRepository messageRepository;
    private final ObjectWriter writer;

    @Autowired
    public MainController(MessageRepository messageRepository, ObjectMapper objectMapper) {
        this.writer = objectMapper.setConfig(objectMapper.getSerializationConfig())
                .writerWithView(Views.FullMessage.class);

        this.messageRepository = messageRepository;
    }


    @GetMapping
    public String main(Model model,
                       @AuthenticationPrincipal User user) throws JsonProcessingException {
        HashMap<Object, Object> data = new HashMap<>();
      if (user != null) {
        String messages = writer.writeValueAsString(messageRepository.findAll());

        data.put("profile", user);
        model.addAttribute("messages", messages);
      } else {
        model.addAttribute("messages", "[]");
      }
        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));

        return "index";
    }
}
