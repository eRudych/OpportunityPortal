package task.NewOpportunityPortal.repository;

import task.NewOpportunityPortal.entity.Category;

import java.util.List;

public interface CategoryRepository {

    Long createCategory(Category Category);

    Category getCategory(Long CategoryId);

    boolean removeCategory(Long CategoryId);

    List<Category> getAllCategories();

}
