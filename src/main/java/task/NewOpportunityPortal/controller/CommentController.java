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
        log.info("Send comment {}", comment.toString());
        return service.createComment(comment);
    }

    @DeleteMapping("/{commentId}")
    public boolean remove(@PathVariable("commentId") long commentId){
        log.info("Remove comment {}", commentId);
        return service.removeComment(commentId);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Comment update(@RequestBody Comment comment){
        log.info("Update comment {}", comment.toString());
        return service.updateComment(comment);
    }

    @GetMapping("/{commentId}")
    public Comment get(@PathVariable("commentId") long commentId){
        log.info("Get comment {}", commentId);
        return service.getComment(commentId);
    }

    @GetMapping("/advert/{advertId}")
    public List<Comment> getCommentAdverts(@PathVariable("advertId") long advertId){
        log.info("Get comment adverts {}", advertId);
        return service.getCommentAdverts(advertId); }

}
