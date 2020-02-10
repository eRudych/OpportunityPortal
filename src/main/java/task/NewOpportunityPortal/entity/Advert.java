package task.NewOpportunityPortal.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class Advert {
    private final Long id;
    private Long creatorId;
    private Long categoryId;
    private Long statusId;
    private String subject;
    private String info;
    private List<Long> workersId;
    private List<Long> tagsId;
    private List<Long> workersHowCheckMark;
    private long mark;
    private String linkToDocument;
    private Timestamp createAt;
}