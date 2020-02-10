package task.NewOpportunityPortal.component.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import task.NewOpportunityPortal.component.entity.Category;
import task.NewOpportunityPortal.component.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping(value = "/components/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService service;

    @GetMapping("/{categoryId}")
    public Category getCategory(@PathVariable("categoryId") long categoryId){
        log.info("Remove category {}", categoryId);
        return service.getCategory(categoryId);
    }
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public long createCategory(@RequestBody Category category){
        log.info("Create category {}", category.toString());
        return service.createCategory(category);
    }

    @DeleteMapping("/{categoryId}")
    public boolean removeCategory(@PathVariable("categoryId") long categoryId){
        log.info("Remove category {}", categoryId);
        return service.removeCategory(categoryId);
    }

    @GetMapping
    public List<Category> getAllCategories(){
        log.info("Get all categories");
        return service.getAllCategories();
    }
}
