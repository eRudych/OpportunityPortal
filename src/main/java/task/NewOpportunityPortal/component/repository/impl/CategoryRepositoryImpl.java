package task.NewOpportunityPortal.component.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import task.NewOpportunityPortal.component.entity.Category;
import task.NewOpportunityPortal.component.repository.CategoryRepository;
import task.NewOpportunityPortal.db.tables.records.CategoryRecord;

import java.util.List;

import static task.NewOpportunityPortal.db.tables.Category.CATEGORY;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CategoryRepositoryImpl implements CategoryRepository {

    private final DSLContext dsl;

    private Long insert(Category category) {
        CategoryRecord categoryRecord = dsl.insertInto(CATEGORY, CATEGORY.NAME)
                .values(category.getName())
                .returning(CATEGORY.ID)
                .fetchOne();
        log.info("Insert into db: {}", category.getName());
        return categoryRecord.getValue(CATEGORY.ID);
    }

    @Override
    public Long createCategory(Category category) {
        log.info("Create category: {}", category.getId());
        return insert(category);
    }

    @Override
    public Category getCategory(Long categoryId) {
        log.info("Select category: {}", categoryId);
        Category category = dsl.selectFrom(CATEGORY)
                .where(CATEGORY.ID.eq(categoryId))
                .fetchOneInto(Category.class);
        log.info("Set selected data: {}", categoryId);
        return category;
    }

    @Override
    public boolean removeCategory(Long categoryId) {
        log.info("Remove category: {}", categoryId);
        try {
            dsl.deleteFrom(CATEGORY)
                    .where(CATEGORY.ID.eq(categoryId)).execute();
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Category> getAllCategories() {
        return dsl.selectFrom(CATEGORY)
                .fetch(r -> new Category(r.get(0, Long.class), r.get(1, String.class)));
    }
}
