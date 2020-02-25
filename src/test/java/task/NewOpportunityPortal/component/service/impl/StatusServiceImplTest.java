package task.NewOpportunityPortal.component.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import task.NewOpportunityPortal.component.entity.Status;
import task.NewOpportunityPortal.component.repository.StatusRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StatusServiceImplTest {

    @InjectMocks
    StatusServiceImpl service;

    @Mock
    StatusRepository repository;

    @Test
    public void createStatus() {
        Status status = new Status(1L, "name");
        service.createStatus(status);
        verify(repository, times(1)).createStatus(status);
    }

    @Test
    public void getStatus() {
        when(repository.getStatus(1L)).thenReturn(new Status(1L, "name"));
        Status status = service.getStatus(1L);
        assertEquals(1, status.getId().intValue());
        assertEquals("name", status.getName());
    }

    @Test
    public void getAllStatuses() {
        List<Status> list = new ArrayList<>();
        Status statusOne = new Status(null, "name1");
        Status statusTwo = new Status(null, "name2");
        Status statusTree = new Status(null, "name3");
        list.add(statusOne);
        list.add(statusTwo);
        list.add(statusTree);
        when(repository.getAllStatuses()).thenReturn(list);
        List<Status> empList = service.getAllStatuses();
        assertEquals(3, empList.size());
        verify(repository, times(1)).getAllStatuses();
    }
}