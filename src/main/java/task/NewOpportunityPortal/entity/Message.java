package task.NewOpportunityPortal.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Value;

import java.sql.Timestamp;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
    final Long id;
    Long authorId;
    Long chatId;
    String text;
    Timestamp createAt;
}
