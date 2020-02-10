package task.NewOpportunityPortal.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.NewOpportunityPortal.entity.Category;
import task.NewOpportunityPortal.entity.Status;
import task.NewOpportunityPortal.entity.Tag;
import task.NewOpportunityPortal.repository.CategoryRepository;
import task.NewOpportunityPortal.repository.StatusRepository;
import task.NewOpportunityPortal.repository.TagRepository;
import task.NewOpportunityPortal.service.ComponentService;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ComponentServiceImpl implements ComponentService {

    private final CategoryRepository categoryRepository;
    private final StatusRepository statusRepository;
    private final TagRepository tagRepository;


    @Override
    public Long createCategory(Category category) {
        log.info("Create category: {}", category.toString());
        return categoryRepository.createCategory(category);
    }

    @Override
    public Category getCategory(Long categoryId) {
        log.info("Get category: {}", categoryId );
        return categoryRepository.getCategory(categoryId);
    }

    @Override
    public boolean removeCategory(Long categoryId) {
        log.info("Remove category: {}", categoryId );
        return categoryRepository.removeCategory(categoryId);
    }

    @Override
    public List<Category> getAllCategories() {
        log.info("Get all categories");
        return categoryRepository.getAllCategories();
    }

    @Override
    public Long createStatus(Status status) {
        log.info("Create status: {}", status.toString());
        return statusRepository.createStatus(status);
    }

    @Override
    public Status getStatus(Long statusId) {
        log.info("Get status: {}", statusId );
        return statusRepository.getStatus(statusId);
    }

    @Override
    public boolean removeStatus(Long statusId) {
        log.info("Remove status: {}", statusId );
        return statusRepository.removeStatus(statusId);
    }

    @Override
    public List<Status> getAllStatuses() {
        log.info("Get all statuses");
        return statusRepository.getAllStatuses();
    }

    @Override
    public Long createTag(Tag tag) {
        log.info("Create tag: {}", tag.toString());
        return tagRepository.createTag(tag);
    }

    @Override
    public Tag getTag(Long tagId) {
        log.info("Get tag: {}", tagId );
        return tagRepository.getTag(tagId);
    }

    @Override
    public boolean removeTag(Long tagId) {
        log.info("Remove tag: {}", tagId );
        return tagRepository.removeTag(tagId);
    }

    @Override
    public List<Tag> getAllTags() {
        log.info("Get all tags");
        return tagRepository.getAllTags();
    }
}
