package task.NewOpportunityPortal.component.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import task.NewOpportunityPortal.component.entity.Tag;
import task.NewOpportunityPortal.component.repository.TagRepository;
import task.NewOpportunityPortal.db.tables.records.TagRecord;

import java.util.List;

import static task.NewOpportunityPortal.db.tables.Tag.TAG;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class TagRepositoryImpl implements TagRepository {

    private final DSLContext dsl;

    private Long insert(Tag tag) {
        TagRecord tagRecord = dsl.insertInto(TAG, TAG.NAME)
                .values(tag.getName())
                .returning(TAG.ID)
                .fetchOne();
        log.info("Insert into db: {}", tag.getName());
        return tagRecord.getValue(TAG.ID);
    }

    @Override
    public Long createTag(Tag tag){
        log.info("Create tag: {}", tag.getId());
        return insert(tag);
    }

    @Override
    public Tag getTag(Long tagId) {
        log.info("Select tag: {}", tagId);
        Tag tag = dsl.selectFrom(TAG)
                .where(TAG.ID.eq(tagId))
                .fetchOneInto(Tag.class);
        log.info("Set selected data: {}", tagId);
        return tag;
    }

    @Override
    public boolean removeTag(Long tagId) {
        log.info("Remove tag: {}", tagId);
        try {
            dsl.deleteFrom(TAG)
                    .where(TAG.ID.eq(tagId)).execute();
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Tag> getAllTags() {
        return dsl.selectFrom(TAG)
                .fetch(r -> new Tag(r.get(0, Long.class), r.get(1, String.class)));
    }
}
