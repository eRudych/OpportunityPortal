package task.NewOpportunityPortal.component.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import task.NewOpportunityPortal.component.entity.Tag;
import task.NewOpportunityPortal.component.repository.TagRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TagServiceImplTest {

    @InjectMocks
    TagServiceImpl service;

    @Mock
    TagRepository repository;

    @org.junit.Test
    public void createTag() {
        Tag tag = new Tag(1L, "name");
        service.createTag(tag);
        verify(repository, times(1)).createTag(tag);
    }

    @org.junit.Test
    public void getTag() {
        when(repository.getTag(1L)).thenReturn(new Tag(1L, "name"));
        Tag tag = service.getTag(1L);
        assertEquals(1, tag.getId().intValue());
        assertEquals("name", tag.getName());
    }

    @Test
    public void getAllTags() {
        List<Tag> list = new ArrayList<>();
        Tag tagOne = new Tag(null, "name1");
        Tag tagTwo = new Tag(null, "name2");
        Tag tagTree = new Tag(null, "name3");
        list.add(tagOne);
        list.add(tagTwo);
        list.add(tagTree);
        when(repository.getAllTags()).thenReturn(list);
        List<Tag> empList = service.getAllTags();
        assertEquals(3, empList.size());
        verify(repository, times(1)).getAllTags();
    }
}