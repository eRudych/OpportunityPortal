package task.NewOpportunityPortal.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import task.NewOpportunityPortal.entity.Advert;
import task.NewOpportunityPortal.repository.AdvertRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class AdvertServiceImplTest {

    @InjectMocks
    AdvertServiceImpl service;

    @Mock
    AdvertRepository repository;

    @Test
    public void createAdvert() {
        LocalDateTime now = LocalDateTime.now();
        Advert advert = new Advert(1L, 5L, 1L, 1L, "subject1", "info1", null, null, null, 0, "link1", Timestamp.valueOf(now));
        service.createAdvert(advert);
        verify(repository, times(1)).createAdvert(advert);
    }

    @Test
    public void updateAdvert() {
        LocalDateTime now = LocalDateTime.now();
        Advert advertCreate = new Advert(1L, 5L, 1L, 1L, "subject1", "info1", null, null, null, 0, "link1", Timestamp.valueOf(now));
        Advert advertUpdate = new Advert(1L, 5L, 1L, 1L, "subject12", "info12", null, null, null, 5, "link12", Timestamp.valueOf(now));
        service.createAdvert(advertCreate);
        service.updateAdvert(advertUpdate);
        verify(repository, times(1)).updateAdvert(advertUpdate);
    }

    @Test
    public void getAdvert() {
        when(repository.getAdvert(1L)).thenReturn(new Advert(1L, 5L, 1L, 1L, "subject1", "info1", null, null, null, 0, "link1", null));
        Advert advert = service.getAdvert(1L);
        assertEquals(1, advert.getId().intValue());
        assertEquals(5, advert.getCreatorId().intValue());
        assertEquals(1, advert.getStatusId().intValue());
        assertEquals("subject1", advert.getSubject());
        assertEquals("info1", advert.getInfo());
    }

    @Test
    public void getAllIdAdverts() {
        List<Long> list = new ArrayList<>();
        Advert advertOne = new Advert(null, 5L, 1L, 1L, "subject1", "info1", null, null, null, 0, "link1", null);
        Advert advertTwo = new Advert(null, 9L, 1L, 1L, "subject2", "info2", null, null, null, 0, "link2", null);
        Advert advertTree = new Advert(null, 7L, 1L, 1L, "subject3", "info3", null, null, null, 0, "link3", null);
        list.add(advertOne.getId());
        list.add(advertTwo.getId());
        list.add(advertTree.getId());
        when(repository.getAllIdAdverts()).thenReturn(list);
        List<Long> empList = service.getAllIdAdverts();
        assertEquals(3, empList.size());
        verify(repository, times(1)).getAllIdAdverts();
    }
}