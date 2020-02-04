package task.NewOpportunityPortal.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class User {
    private final Long id;
    private String login;
    private String password;
    private String name;
    private String nick;
    private Timestamp createAt;
}
