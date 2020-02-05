package task.NewOpportunityPortal.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.LinkedHashSet;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class Chat {
    private final Long id;
    private Long creatorId;
    private Long advertId;
    private String name;
    private LinkedHashSet<Long> usersId;
    private Timestamp createAt;
}
