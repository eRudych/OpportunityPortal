package task.NewOpportunityPortal.ability.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserAbility {
    private final Long id;
    private Long authorId;
    private Long userId;
    private Long commentId;
    private int assessment;
    private Timestamp createAt;
}
