package task.NewOpportunityPortal.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class Message {
    private final Long id;
    private Long authorId;
    private Long chatId;
    private String text;
    private Timestamp createAt;
}
