package task.NewOpportunityPortal.repository;

import task.NewOpportunityPortal.entity.Advert;

public interface AdvertRepository {

    Long createAdvert(Advert advert);

    Advert getAdvert(Long advertId);

    Advert updateAdvert(Advert advert);

    boolean removeAdvert(Long advertId);
}
