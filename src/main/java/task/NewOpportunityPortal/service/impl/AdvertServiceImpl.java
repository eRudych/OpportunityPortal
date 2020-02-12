package task.NewOpportunityPortal.service.impl;

import task.NewOpportunityPortal.entity.Advert;
import task.NewOpportunityPortal.repository.AdvertRepository;
import task.NewOpportunityPortal.service.AdvertService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdvertServiceImpl implements AdvertService {

    private final AdvertRepository repository;

    @Override
    public Long createAdvert(Advert advert) {
        Date now = new java.util.Date();
        log.info("Set time creates: {}",  now);
        advert.setCreateAt(new java.sql.Timestamp(now.getTime()));
        log.info("Create advert: {}",  advert.getId());
        return repository.createAdvert(advert);
    }

    @Override
    public Advert updateAdvert(Advert advert) {
        log.info("Update advert: {}",  advert.getId());
        return repository.updateAdvert(advert);
    }

    @Override
    public Advert getAdvert(Long advertId) {
        log.info("Get advert: {}", advertId);
        return repository.getAdvert(advertId);
    }

    @Override
    public boolean removeAdvert(Long advertId) {
        log.info("Remove advert: {}",  advertId);
        return repository.removeAdvert(advertId);
    }

    @Override
    public List<Long> getAllIdAdverts() {
        log.info("Get all adverts");
        return repository.getAllIdAdverts();
    }
}
