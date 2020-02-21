package task.NewOpportunityPortal.component.repository;

import task.NewOpportunityPortal.component.entity.Category;

import java.util.List;

public interface CategoryRepository {

    Long createCategory(Category Category);

    Category getCategory(Long CategoryId);

    void removeCategory(Long CategoryId);

    List<Category> getAllCategories();

}
