package task.NewOpportunityPortal.component.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import task.NewOpportunityPortal.component.entity.Category;
import task.NewOpportunityPortal.component.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImplTest {

    @InjectMocks
    CategoryServiceImpl service;

    @Mock
    CategoryRepository repository;

    @Test
    public void createCategory() {
        Category Category = new Category(1L, "name");
        service.createCategory(Category);
        verify(repository, times(1)).createCategory(Category);
    }

    @Test
    public void getCategory() {
        when(repository.getCategory(1L)).thenReturn(new Category(1L, "name"));
        Category category = service.getCategory(1L);
        assertEquals(1, category.getId().intValue());
        assertEquals("name", category.getName());
    }

    @Test
    public void getAllCategories() {
        List<Category> list = new ArrayList<>();
        Category categoryOne = new Category(null, "name1");
        Category categoryTwo = new Category(null, "name2");
        Category categoryTree = new Category(null, "name3");
        list.add(categoryOne);
        list.add(categoryTwo);
        list.add(categoryTree);
        when(repository.getAllCategories()).thenReturn(list);
        List<Category> empList = service.getAllCategories();
        assertEquals(3, empList.size());
        verify(repository, times(1)).getAllCategories();
    }
}