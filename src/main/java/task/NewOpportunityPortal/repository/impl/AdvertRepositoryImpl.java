package task.NewOpportunityPortal.repository.impl;

import org.jooq.DSLContext;
import task.NewOpportunityPortal.db.tables.records.AdvertRecord;
import task.NewOpportunityPortal.entity.Advert;
import task.NewOpportunityPortal.repository.AdvertRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

import static task.NewOpportunityPortal.db.tables.Advert.ADVERT;

@Repository
@RequiredArgsConstructor
@Slf4j
public class AdvertRepositoryImpl implements AdvertRepository {

    private final DSLContext dsl;

    private Long insert(Advert advert) {
        AdvertRecord advertsRecord = dsl.insertInto(ADVERT, ADVERT.CREATORID, ADVERT.CATEGORYID, ADVERT.SUBJECT, ADVERT.INFO, ADVERT.STATUSID, ADVERT.LINKTODOC,
                ADVERT.AVERAGEMARK, ADVERT.AMOUNTOFMARKS, ADVERT.WORKERSID, ADVERT.TAGSID, ADVERT.CREATED_AT)
                .values(advert.getCreatorId(),
                        advert.getCategoryId(),
                        advert.getSubject(),
                        advert.getInfo(),
                        advert.getStatusId(),
                        advert.getLinkToDocument(),
                        advert.getMark(),
                        advert.getWorkersHowCheckMark().toArray(new Long[advert.getWorkersHowCheckMark().size()]),
                        advert.getWorkersId().toArray(new Long[advert.getWorkersId().size()]),
                        advert.getTagsId().toArray(new Long[advert.getTagsId().size()]),
                        advert.getCreateAt())
                .returning(ADVERT.ID)
                .fetchOne();
        log.info("Insert into db: {}", advert.toString());
        return advertsRecord.getValue(ADVERT.ID);
    }

    @Override
    public Long createAdvert(Advert advert) {
        log.info("Create advert: {}", advert.getId());
        return insert(advert);
    }

    @Override
    public Advert getAdvert(Long advertId) {
        log.info("Select advert: {}", advertId);
        Advert advert = dsl.selectFrom(ADVERT)
                .where(ADVERT.ID.eq(advertId))
                .fetchOneInto(Advert.class);
        log.info("Set selected data: {}", advertId);
        advert.setCreateAt(dsl.select(ADVERT.CREATED_AT).from(ADVERT).where(ADVERT.ID.eq(advertId)).fetchOneInto((Timestamp.class)));
        return advert;
    }

    @Override
    public Advert updateAdvert(Advert advert) {
        log.info("Update text advert: {}", advert.getId());
        return getAdvert((long) dsl.update(ADVERT)
                .set(ADVERT.INFO, advert.getInfo())
                .set(ADVERT.SUBJECT, advert.getSubject())
                .set(ADVERT.STATUSID, advert.getStatusId())
                .set(ADVERT.LINKTODOC, advert.getLinkToDocument())
                .set(ADVERT.AMOUNTOFMARKS, advert.getWorkersHowCheckMark().toArray(new Long[advert.getWorkersHowCheckMark().size()]))
                .set(ADVERT.AVERAGEMARK, advert.getMark())
                .set(ADVERT.TAGSID, advert.getTagsId().toArray(new Long[advert.getTagsId().size()]))
                .set(ADVERT.WORKERSID, (advert.getWorkersId().toArray(new Long[advert.getWorkersId().size()])))
                .where(ADVERT.ID.eq(advert.getId())).execute());
    }

    @Override
    public boolean removeAdvert(Long advertId) {
        log.info("Remove advert: {}", advertId);
        try {
            dsl.deleteFrom(ADVERT)
                    .where(ADVERT.ID.eq(advertId)).execute();
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Long> getAllIdAdverts() {
        log.info("Get all adverts from db");
        return dsl.selectFrom(ADVERT)
                .orderBy(ADVERT.CREATED_AT)
                .fetch(r -> r.get(0, Long.class));
    }
}
