package task.NewOpportunityPortal.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import task.NewOpportunityPortal.db.tables.records.StatusRecord;
import task.NewOpportunityPortal.entity.Status;
import task.NewOpportunityPortal.repository.StatusRepository;

import java.util.List;

import static task.NewOpportunityPortal.db.tables.Status.STATUS;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class StatusRepositoryImp implements StatusRepository {

    private final DSLContext dsl;

    private Long insert(Status status) {
        StatusRecord statusRecord = dsl.insertInto(STATUS, STATUS.NAME)
                .values(status.getName())
                .returning(STATUS.ID)
                .fetchOne();
        log.info("Insert into db: {}", status.getName());
        return statusRecord.getValue(STATUS.ID);
    }

    @Override
    public Long createStatus(Status status) {
        log.info("Create status: {}", status.getId());
        return insert(status);
    }

    @Override
    public Status getStatus(Long statusId) {
        log.info("Select status: {}", statusId);
        Status status = dsl.selectFrom(STATUS)
                .where(STATUS.ID.eq(statusId))
                .fetchOneInto(Status.class);
        log.info("Set selected data: {}", statusId);
        return status;
    }

    @Override
    public boolean removeStatus(Long statusId) {
        log.info("Remove status: {}", statusId);
        try {
            dsl.deleteFrom(STATUS)
                    .where(STATUS.ID.eq(statusId)).execute();
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Status> getAllStatuses() {
        return dsl.selectFrom(STATUS)
                .fetch(r -> new Status(r.get(0, Long.class), r.get(1, String.class)));
    }
}
