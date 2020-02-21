package task.NewOpportunityPortal.component.service;

import task.NewOpportunityPortal.component.entity.Category;

import java.util.List;

public interface CategoryService {

    Long createCategory(Category Category);

    Category getCategory(Long CategoryId);

    void removeCategory(Long CategoryId);

    List<Category> getAllCategories();
}
