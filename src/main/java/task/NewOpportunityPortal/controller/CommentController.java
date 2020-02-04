package task.NewOpportunityPortal.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import task.NewOpportunityPortal.entity.Comment;
import task.NewOpportunityPortal.service.CommentService;

import java.util.List;

@RestController
@RequestMapping(value = "/comments")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService service;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public long send(@RequestBody Comment comment){
        return service.createComment(comment);
    }

    @DeleteMapping("/{id}")
    public boolean remove(@PathVariable("id") long id){
        return service.removeComment(id);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Comment update(@RequestBody Comment comment){
        return service.updateComment(comment);
    }

    @GetMapping("/{id}")
    public Comment get(@PathVariable("id") long id){
        return service.getComment(id);
    }

    @GetMapping("/advert/{advertId}")
    public List<Comment> getCommentAdverts(@PathVariable("advertId") long advertId){ return service.getCommentAdverts(advertId); }

}
