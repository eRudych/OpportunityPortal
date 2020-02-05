package task.NewOpportunityPortal.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.LinkedHashSet;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class Advert {
    private final Long id;
    private Long creatorId;
    private Long profileId;
    private Long categoryId;
    private Long statusId;
    private String subject;
    private String info;
    private LinkedHashSet<Long> workersId;
    private LinkedHashSet<Long> tagsId;
    private LinkedHashSet<Long> workersHowCheckMark;
    private int mark;
    private String linkToDocument;
    private Timestamp createAt;
}
