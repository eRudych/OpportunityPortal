package task.NewOpportunityPortal.repository.impl;

import org.jooq.DSLContext;
import task.NewOpportunityPortal.db.tables.records.AdvertsRecord;
import task.NewOpportunityPortal.entity.Advert;
import task.NewOpportunityPortal.repository.AdvertRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

import static task.NewOpportunityPortal.db.tables.Adverts.ADVERTS;

@Repository
@RequiredArgsConstructor
@Slf4j
public class AdvertRepositoryImpl implements AdvertRepository {

    private final DSLContext dsl;

    private Long insert(Advert advert) {
        AdvertsRecord advertsRecord = dsl.insertInto(ADVERTS, ADVERTS.CREATORID, ADVERTS.CATEGORYID, ADVERTS.SUBJECT, ADVERTS.INFO, ADVERTS.STATUSID, ADVERTS.LINKTODOC,
                ADVERTS.AVERAGEMARK, ADVERTS.AMOUNTOFMARKS, ADVERTS.WORKERSID, ADVERTS.TAGSID, ADVERTS.CREATED_AT)
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
                .returning(ADVERTS.ID)
                .fetchOne();
        log.info("Insert into db: {}", advert.toString());
        return advertsRecord.getValue(ADVERTS.ID);
    }

    @Override
    public Long createAdvert(Advert advert) {
        log.info("Create advert: {}", advert.getId());
        return insert(advert);
    }

    @Override
    public Advert getAdvert(Long advertId) {
        log.info("Select advert: {}", advertId);
        Advert advert = dsl.selectFrom(ADVERTS)
                .where(ADVERTS.ID.eq(advertId))
                .fetchOneInto(Advert.class);
        log.info("Set selected data: {}", advertId);
        advert.setCreateAt(dsl.select(ADVERTS.CREATED_AT).from(ADVERTS).where(ADVERTS.ID.eq(advertId)).fetchOneInto((Timestamp.class)));
        return advert;
    }

    @Override
    public Advert updateAdvert(Advert advert) {
        log.info("Update text advert: {}", advert.getId());
        return getAdvert((long) dsl.update(ADVERTS)
                .set(ADVERTS.INFO, advert.getInfo())
                .set(ADVERTS.SUBJECT, advert.getSubject())
                .set(ADVERTS.STATUSID, advert.getStatusId())
                .set(ADVERTS.LINKTODOC, advert.getLinkToDocument())
                .set(ADVERTS.AMOUNTOFMARKS, advert.getWorkersHowCheckMark().toArray(new Long[advert.getWorkersHowCheckMark().size()]))
                .set(ADVERTS.AVERAGEMARK, advert.getMark())
                .set(ADVERTS.TAGSID, advert.getTagsId().toArray(new Long[advert.getTagsId().size()]))
                .set(ADVERTS.WORKERSID, (advert.getWorkersId().toArray(new Long[advert.getWorkersId().size()])))
                .where(ADVERTS.ID.eq(advert.getId())).execute());
    }

    @Override
    public boolean removeAdvert(Long advertId) {
        log.info("Remove advert: {}", advertId);
        try {
            dsl.deleteFrom(ADVERTS)
                    .where(ADVERTS.ID.eq(advertId)).execute();
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Long> getAllIdAdverts() {
        log.info("Get all adverts from db");
        return dsl.selectFrom(ADVERTS)
                .orderBy(ADVERTS.CREATED_AT)
                .fetch(r -> r.get(0, Long.class));
    }
}
