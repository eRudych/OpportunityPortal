package task.NewOpportunityPortal.repository;

import task.NewOpportunityPortal.entity.Tag;

import java.util.List;

public interface TagRepository {

    Long createTag(Tag tag);

    Tag getTag(Long tagId);

    boolean removeTag(Long tagId);

    List<Tag> getAllTags();
}
