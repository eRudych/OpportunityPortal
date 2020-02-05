package task.NewOpportunityPortal.service;

import task.NewOpportunityPortal.entity.Advert;

public interface AdvertService {

    Long createAdvert(Advert advert);

    Advert updateAdvert(Advert advert);

    Advert getAdvert(Long advertId);

    boolean removeAdvert(Long advertId);
}
