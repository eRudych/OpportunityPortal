package task.NewOpportunityPortal.component.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import task.NewOpportunityPortal.component.entity.Status;
import task.NewOpportunityPortal.component.repository.StatusRepository;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StatusServiceImplTest {

    @InjectMocks
    private StatusServiceImpl service;

    @Mock
    private StatusRepository repository;

    private List<Status> list;
    private Status status;
    private Long statusId;

    @Before
    public void init() {
        this.list = new ArrayList<>();
        Status statusOne = new Status(null, "name1");
        Status statusTwo = new Status(null, "name2");
        Status statusTree = new Status(null, "name3");
        this.list.add(statusOne);
        this.list.add(statusTwo);
        this.list.add(statusTree);
        this.status = new Status(null, "name");
        this.statusId = 1L;
    }

    @Test
    public void testCreateStatus() {
        service.createStatus(status);
        verify(repository).createStatus(status);
    }

    @Test
    public void testGetStatus() {
        when(repository.getStatus(eq(statusId))).thenReturn(status);
        Status getStatus = service.getStatus(statusId);
        assertThat(status, samePropertyValuesAs(getStatus));
    }

    @Test
    public void testGetAllStatuses() {
        when(repository.getAllStatuses()).thenReturn(list);
        List<Status> empList = service.getAllStatuses();
        assertThat(3, is(empList.size()));
        verify(repository).getAllStatuses();
    }
}