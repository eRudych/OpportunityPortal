package task.NewOpportunityPortal.component.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import task.NewOpportunityPortal.component.entity.Tag;
import task.NewOpportunityPortal.component.repository.TagRepository;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TagServiceImplTest {

    @InjectMocks
    private TagServiceImpl service;

    @Mock
    private TagRepository repository;

    private List<Tag> ids;
    private Tag tag;
    private Long tagId;

    @Before
    public void init() {
        this.ids = new ArrayList<>();
        Tag tagOne = new Tag(null, "name1");
        Tag tagTwo = new Tag(null, "name2");
        Tag tagTree = new Tag(null, "name3");
        this.ids.add(tagOne);
        this.ids.add(tagTwo);
        this.ids.add(tagTree);
        this.tag = new Tag(null, "name");
        this.tagId=1L;
    }

    @Test
    public void testCreateTag() {
        service.createTag(tag);
        verify(repository).createTag(tag);
    }

    @Test
    public void testGetTag() {
        when(repository.getTag(eq(tagId))).thenReturn(tag);
        Tag getTag = service.getTag(tagId);
        assertThat(tag, samePropertyValuesAs(getTag));
    }

    @Test
    public void testGetAllTags() {
        when(repository.getAllTags()).thenReturn(ids);
        List<Tag> empList = service.getAllTags();
        assertThat(empList.size(), is(ids.size()));
        verify(repository).getAllTags();
    }
}