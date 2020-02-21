package task.NewOpportunityPortal.component.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import task.NewOpportunityPortal.component.entity.Status;
import task.NewOpportunityPortal.component.service.StatusService;

import java.util.List;

@RestController
@RequestMapping(value = "/components/statuses")
@RequiredArgsConstructor
@Slf4j
public class StatusController {

    private final StatusService service;

    @GetMapping("/{statusId}")
    public Status getStatus(@PathVariable("statusId") long statusId) {
        log.info("Remove status {}", statusId);
        return service.getStatus(statusId);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public long createStatus(@RequestBody Status status) {
        log.info("Create status {}", status.toString());
        return service.createStatus(status);
    }

    @DeleteMapping("/{statusId}")
    public void removeStatus(@PathVariable("statusId") long statusId) {
        log.info("Remove status {}", statusId);
        service.removeStatus(statusId);
    }

    @GetMapping
    public List<Status> getAllStatuses() {
        log.info("Get all statuses");
        return service.getAllStatuses();
    }
}
