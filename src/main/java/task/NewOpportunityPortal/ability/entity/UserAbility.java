package task.NewOpportunityPortal.ability.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Value;

import java.sql.Timestamp;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAbility {
    final Long id;
    Long authorId;
    Long userId;
    int assessment;
    Timestamp createAt;
}
