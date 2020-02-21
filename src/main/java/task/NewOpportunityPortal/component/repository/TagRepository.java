package task.NewOpportunityPortal.component.repository;

import task.NewOpportunityPortal.component.entity.Tag;

import java.util.List;

public interface TagRepository {

    Long createTag(Tag tag);

    Tag getTag(Long tagId);

    void removeTag(Long tagId);

    List<Tag> getAllTags();
}
