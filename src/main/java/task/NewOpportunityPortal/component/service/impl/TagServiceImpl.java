package task.NewOpportunityPortal.component.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import task.NewOpportunityPortal.component.entity.Tag;
import task.NewOpportunityPortal.component.repository.TagRepository;
import task.NewOpportunityPortal.component.service.TagService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TagServiceImpl implements TagService {

    private final TagRepository repository;

    @Override
    @Cacheable(value = "tags", key = "#tag.id")
    public Long createTag(Tag tag) {
        log.info("Create tag: {}", tag.toString());
        return repository.createTag(tag);
    }

    @Override
    @Cacheable("tags")
    public Tag getTag(Long tagId) {
        log.info("Get tag: {}", tagId );
        return repository.getTag(tagId);
    }

    @Override
    @CacheEvict("tags")
    public boolean removeTag(Long tagId) {
        log.info("Remove tag: {}", tagId );
        return repository.removeTag(tagId);
    }

    @Override
    public List<Tag> getAllTags() {
        log.info("Get all tags");
        return repository.getAllTags();
    }
}
