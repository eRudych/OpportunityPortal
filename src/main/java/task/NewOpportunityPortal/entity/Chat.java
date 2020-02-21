package task.NewOpportunityPortal.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Value;

import java.sql.Timestamp;
import java.util.List;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class Chat {
    final Long id;
    Long creatorId;
    Long advertId;
    String name;
    List<Long> usersId;
    Timestamp createAt;
}
