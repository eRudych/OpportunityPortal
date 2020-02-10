package task.NewOpportunityPortal.component.service;

import task.NewOpportunityPortal.component.entity.Tag;

import java.util.List;

public interface TagService {

    Long createTag(Tag tag);

    Tag getTag(Long tagId);

    boolean removeTag(Long tagId);

    List<Tag> getAllTags();
}
