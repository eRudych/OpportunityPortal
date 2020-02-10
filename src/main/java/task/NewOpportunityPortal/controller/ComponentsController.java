package task.NewOpportunityPortal.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import task.NewOpportunityPortal.entity.Category;
import task.NewOpportunityPortal.entity.Status;
import task.NewOpportunityPortal.entity.Tag;
import task.NewOpportunityPortal.service.ComponentService;

import java.util.List;


@RestController
@RequestMapping(value = "/components")
@RequiredArgsConstructor
@Slf4j
public class ComponentsController {

    private final ComponentService service;

    @GetMapping("/categories/{categoryId}")
    public Category getCategory(@PathVariable("categoryId") long categoryId){
        log.info("Remove category {}", categoryId);
        return service.getCategory(categoryId);
    }
    @PostMapping(value = "/categories", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public long createCategory(@RequestBody Category category){
        log.info("Create category {}", category.toString());
        return service.createCategory(category);
    }

    @DeleteMapping("/categories/{categoryId}")
    public boolean removeCategory(@PathVariable("categoryId") long categoryId){
        log.info("Remove category {}", categoryId);
        return service.removeCategory(categoryId);
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories(){
        log.info("Get all categories");
        return service.getAllCategories();
    }

    @GetMapping("/statuses/{statusId}")
    public Status getStatus(@PathVariable("statusId") long statusId){
        log.info("Remove status {}", statusId);
        return service.getStatus(statusId);
    }
    @PostMapping(value = "/statuses", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public long createStatus(@RequestBody Status status){
        log.info("Create status {}", status.toString());
        return service.createStatus(status);
    }

    @DeleteMapping("/statuses/{statusId}")
    public boolean removeStatus(@PathVariable("statusId") long statusId){
        log.info("Remove status {}", statusId);
        return service.removeStatus(statusId);
    }

    @GetMapping("/statuses")
    public List<Status> getAllStatuses(){
        log.info("Get all statuses");
        return service.getAllStatuses();
    }

    @GetMapping("/tags/{tagId}")
    public Tag getTag(@PathVariable("tagId") long tagId){
        log.info("Remove tag {}", tagId);
        return service.getTag(tagId);
    }
    @PostMapping(value = "/tags", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public long createTag(@RequestBody Tag tag){
        log.info("Create tag {}", tag.toString());
        return service.createTag(tag);
    }

    @DeleteMapping("/tags/{tagId}")
    public boolean removeTag(@PathVariable("tagId") long tagId){
        log.info("Remove tag {}", tagId);
        return service.removeTag(tagId);
    }

    @GetMapping("/tags")
    public List<Tag> getAllTags(){
        log.info("Get all tags");
        return service.getAllTags();
    }
}
