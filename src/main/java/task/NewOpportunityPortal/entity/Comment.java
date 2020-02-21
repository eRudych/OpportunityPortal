package task.NewOpportunityPortal.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Value;

import java.sql.Timestamp;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment {
    final Long id;
    Long creatorId;
    Long advertId;
    String text;
    Timestamp createAt;
}
