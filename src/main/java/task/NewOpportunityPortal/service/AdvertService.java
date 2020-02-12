package task.NewOpportunityPortal.service;

import task.NewOpportunityPortal.entity.Advert;

import java.util.List;

public interface AdvertService {

    Long createAdvert(Advert advert);

    Advert updateAdvert(Advert advert);

    Advert getAdvert(Long advertId);

    boolean removeAdvert(Long advertId);

    List<Long> getAllIdAdverts();
}
