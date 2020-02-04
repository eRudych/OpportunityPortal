package task.NewOpportunityPortal.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class Comment {
    private final Long id;
    private Long creatorId;
    private Long advertId;
    private String text;
    private Timestamp creteAt;
}
