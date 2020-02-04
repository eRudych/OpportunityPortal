package task.NewOpportunityPortal.service.impl;

import task.NewOpportunityPortal.entity.Advert;
import task.NewOpportunityPortal.repository.AdvertRepository;
import task.NewOpportunityPortal.service.AdvertService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AdvertServiceImpl implements AdvertService {

    private final AdvertRepository repository;

    @Override
    public Long createAdvert(Advert advert) {
        log.info("create advert "+ advert.getId());
        return repository.createAdvert(advert);
    }

    @Override
    public Advert updateAdvert(Advert advert) {
        log.info("update advert "+ advert.getId());
        return repository.updateAdvert(advert);
    }

    @Override
    public Advert getAdvert(Long advertId) {
        log.info("get advert "+advertId);
        return repository.getAdvert(advertId);
    }

    @Override
    public boolean removeAdvert(Long advertId) {
        log.info("remove advert "+ advertId);
        return repository.removeAdvert(advertId);
    }
}
