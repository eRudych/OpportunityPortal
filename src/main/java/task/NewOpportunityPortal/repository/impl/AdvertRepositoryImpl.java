package task.NewOpportunityPortal.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import task.NewOpportunityPortal.db.tables.records.AdvertsRecord;
import task.NewOpportunityPortal.entity.Advert;
import task.NewOpportunityPortal.repository.AdvertRepository;

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
        log.info("Create advert: {}", advert.toString());
        return insert(advert);
    }

    @Override
    public Advert getAdvert(Long advertId) {
        log.info("Select advert: {}", advertId);
        return dsl.selectFrom(ADVERTS)
                .where(ADVERTS.ID.eq(advertId))
                .fetchOne(r -> new Advert(
                        r.get(0, Long.class),
                        r.get(1, Long.class),
                        r.get(2, Long.class),
                        r.get(7, Long.class),
                        r.get(4, String.class),
                        r.get(5, String.class),
                        r.get(3, List.class),
                        r.get(8, List.class),
                        r.get(9, List.class),
                        r.get(10, Long.class),
                        r.get(6, String.class),
                        r.get(11, Timestamp.class)
                ));
    }

    @Override
    public Advert updateAdvert(Advert advert) {
        log.info("Update text advert: {}", advert.toString());
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
    public void removeAdvert(Long advertId) {
        log.info("Remove advert: {}", advertId);
        dsl.deleteFrom(ADVERTS)
                .where(ADVERTS.ID.eq(advertId))
                .execute();
    }

    @Override
    public List<Long> getAllIdAdverts() {
        log.info("Get all adverts from db");
        return dsl.selectFrom(ADVERTS)
                .orderBy(ADVERTS.CREATED_AT)
                .fetch(r -> r.get(0, Long.class));
    }
}
