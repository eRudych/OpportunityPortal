package task.NewOpportunityPortal.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import task.NewOpportunityPortal.entity.Advert;
import task.NewOpportunityPortal.repository.AdvertRepository;
import task.NewOpportunityPortal.service.AdvertService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdvertServiceImpl implements AdvertService {

    private final AdvertRepository repository;

    @Override
    public Long createAdvert(Advert advert) {
        LocalDateTime now = LocalDateTime.now();
        log.info("Create advert: {}", advert.toString());
        return repository.createAdvert(
                new Advert(
                        advert.getId(),
                        advert.getCreatorId(),
                        advert.getCategoryId(),
                        advert.getStatusId(),
                        advert.getSubject(),
                        advert.getInfo(),
                        advert.getWorkersId(),
                        advert.getTagsId(),
                        advert.getWorkersHowCheckMark(),
                        advert.getMark(),
                        advert.getLinkToDocument(),
                        java.sql.Timestamp.valueOf(now)));
    }

    @Override
    @CachePut(value = "adverts", key = "#advert.id")
    public Advert updateAdvert(Advert advert) {
        log.info("Update advert: {}", advert.toString());
        return repository.updateAdvert(advert);
    }

    @Override
    @Cacheable("adverts")
    public Advert getAdvert(Long advertId) {
        log.info("Get advert: {}", advertId);
        return repository.getAdvert(advertId);
    }

    @Override
    @CacheEvict("adverts")
    public void removeAdvert(Long advertId) {
        log.info("Remove advert: {}", advertId);
        repository.removeAdvert(advertId);
    }

    @Override
    public List<Long> getAllIdAdverts() {
        log.info("Get all adverts");
        return repository.getAllIdAdverts();
    }
}
