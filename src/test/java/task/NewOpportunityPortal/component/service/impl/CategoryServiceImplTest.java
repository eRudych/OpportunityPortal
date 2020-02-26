package task.NewOpportunityPortal.component.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import task.NewOpportunityPortal.component.entity.Category;
import task.NewOpportunityPortal.component.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl service;

    @Mock
    private CategoryRepository repository;

    private List<Category> list;
    private Category category;
    private Long categoryId;

    @Before
    public void init() {
        this.list = new ArrayList<>();
        Category categoryOne = new Category(null, "name1");
        Category categoryTwo = new Category(null, "name2");
        Category categoryTree = new Category(null, "name3");
        this.list.add(categoryOne);
        this.list.add(categoryTwo);
        this.list.add(categoryTree);
        this.category = new Category(null, "name");
        this.categoryId = 1L;
    }

    @Test
    public void testCreateCategory() {
        service.createCategory(category);
        verify(repository).createCategory(category);
    }

    @Test
    public void testGetCategory() {
        when(repository.getCategory(eq(categoryId))).thenReturn(category);
        Category getCategory = service.getCategory(categoryId);
        assertThat(category, samePropertyValuesAs(getCategory));
    }

    @Test
    public void testGetAllCategories() {
        when(repository.getAllCategories()).thenReturn(list);
        List<Category> empList = service.getAllCategories();
        assertThat(3, is(empList.size()));
        verify(repository).getAllCategories();
    }
}