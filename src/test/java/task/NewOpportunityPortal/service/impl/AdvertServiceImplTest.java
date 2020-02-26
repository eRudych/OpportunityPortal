package task.NewOpportunityPortal.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import task.NewOpportunityPortal.entity.Advert;
import task.NewOpportunityPortal.repository.AdvertRepository;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class AdvertServiceImplTest {

    @InjectMocks
    private AdvertServiceImpl service;

    @Mock
    private AdvertRepository repository;

    private List<Long> list;
    private Advert advert;
    private Advert advertUpdate;
    private Long advertId;

    @Before
    public void init() {
        this.list = new ArrayList<>();
        Advert advertOne = new Advert(1L, 5L, 1L, 1L, "subject1", "info1", null, null, null, 0, "link1", null);
        Advert advertTwo = new Advert(2L, 9L, 1L, 1L, "subject2", "info2", null, null, null, 0, "link2", null);
        Advert advertTree = new Advert(3L, 7L, 1L, 1L, "subject3", "info3", null, null, null, 0, "link3", null);
        this.list.add(advertOne.getId());
        this.list.add(advertTwo.getId());
        this.list.add(advertTree.getId());
        this.advert = new Advert(null, 5L, 1L, 1L, "subject1", "info1", null, null, null, 0, "link1", null);
        this.advertUpdate = new Advert(null, 5L, 1L, 1L, "subject12", "info12", null, null, null, 5, "link12", null);
    }

    @Test
    public void testCreateAdvert() {
        ArgumentCaptor<Advert> advertArgs = ArgumentCaptor.forClass(Advert.class);
        service.createAdvert(advert);
        verify(repository).createAdvert(advertArgs.capture());
    }

    @Test
    public void testUpdateAdvert() {
        service.updateAdvert(advertUpdate);
        verify(repository).updateAdvert(advertUpdate);
    }

    @Test
    public void testGetAdvert() {
        when(repository.getAdvert(eq(advertId))).thenReturn(advert);
        Advert getAdvert = service.getAdvert(advertId);
        assertThat(advert, samePropertyValuesAs(getAdvert, "create_at"));
    }

    @Test
    public void testGetAllIdAdverts() {
        when(repository.getAllIdAdverts()).thenReturn(list);
        List<Long> empList = service.getAllIdAdverts();
        assertThat(3, is(empList.size()));
        verify(repository).getAllIdAdverts();
    }
}