package sugar.sugardemo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import sugar.sugardemo.dto.EventType;
import sugar.sugardemo.dto.ObjectType;
import sugar.sugardemo.dto.WsEventDTO;

import java.util.function.BiConsumer;

@Component
@AllArgsConstructor
public class WsSender {

    private final SimpMessagingTemplate template;
    private final ObjectMapper objectMapper;

    public <T> BiConsumer<EventType, T> getSender(ObjectType objectType, Class view) {
        ObjectWriter writer = this.objectMapper.setConfig(this.objectMapper.getSerializationConfig())
                .writerWithView(view);

        return (EventType eventType, T payload) -> {
            String value;
            try {
                value = writer.writeValueAsString(payload);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            template.convertAndSend("/topic/activity",
                    new WsEventDTO(objectType, eventType, value));
        };
    }
}
