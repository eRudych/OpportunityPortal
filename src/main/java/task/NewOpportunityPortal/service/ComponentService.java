package task.NewOpportunityPortal.service;

import task.NewOpportunityPortal.entity.Category;
import task.NewOpportunityPortal.entity.Status;
import task.NewOpportunityPortal.entity.Tag;

import java.util.List;

public interface ComponentService {

    Long createCategory(Category Category);

    Category getCategory(Long CategoryId);

    boolean removeCategory(Long CategoryId);

    List<Category> getAllCategories();

    Long createStatus(Status status);

    Status getStatus(Long statusId);

    boolean removeStatus(Long statusId);

    List<Status> getAllStatuses();

    Long createTag(Tag tag);

    Tag getTag(Long tagId);

    boolean removeTag(Long tagId);

    List<Tag> getAllTags();
}
