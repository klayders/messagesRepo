package sugar.sugardemo.dto;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sugar.sugardemo.domain.Views;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonView(Views.Id.class)
public class WsEventDTO {
    private ObjectType objectType;
    private EventType eventType;

    @JsonRawValue
    private String body;
}
