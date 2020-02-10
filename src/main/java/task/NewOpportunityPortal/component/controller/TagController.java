package task.NewOpportunityPortal.component.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import task.NewOpportunityPortal.component.entity.Tag;
import task.NewOpportunityPortal.component.service.TagService;

import java.util.List;

@RestController
@RequestMapping(value = "/components/tags")
@RequiredArgsConstructor
@Slf4j
public class TagController {

    private final TagService service;

    @GetMapping("/{tagId}")
    public Tag getTag(@PathVariable("tagId") long tagId){
        log.info("Remove tag {}", tagId);
        return service.getTag(tagId);
    }
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public long createTag(@RequestBody Tag tag){
        log.info("Create tag {}", tag.toString());
        return service.createTag(tag);
    }

    @DeleteMapping("/{tagId}")
    public boolean removeTag(@PathVariable("tagId") long tagId){
        log.info("Remove tag {}", tagId);
        return service.removeTag(tagId);
    }

    @GetMapping
    public List<Tag> getAllTags(){
        log.info("Get all tags");
        return service.getAllTags();
    }
}
