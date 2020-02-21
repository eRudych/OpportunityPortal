package task.NewOpportunityPortal.component.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import task.NewOpportunityPortal.component.entity.Category;
import task.NewOpportunityPortal.component.repository.CategoryRepository;
import task.NewOpportunityPortal.component.service.CategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Override
    public Long createCategory(Category category) {
        log.info("Create category: {}", category.toString());
        return repository.createCategory(category);
    }

    @Override
    @Cacheable("categories")
    public Category getCategory(Long categoryId) {
        log.info("Get category: {}", categoryId);
        return repository.getCategory(categoryId);
    }

    @Override
    @CacheEvict("categories")
    public void removeCategory(Long categoryId) {
        log.info("Remove category: {}", categoryId);
        repository.removeCategory(categoryId);
    }

    @Override
    public List<Category> getAllCategories() {
        log.info("Get all categories");
        return repository.getAllCategories();
    }
}
