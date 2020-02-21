package task.NewOpportunityPortal.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Value;

import java.sql.Timestamp;
import java.util.List;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class Advert {
    final Long id;
    Long creatorId;
    Long categoryId;
    Long statusId;
    String subject;
    String info;
    List<Long> workersId;
    List<Long> tagsId;
    List<Long> workersHowCheckMark;
    long mark;
    String linkToDocument;
    Timestamp createAt;
}