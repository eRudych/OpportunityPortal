package task.NewOpportunityPortal.component.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import task.NewOpportunityPortal.component.entity.Status;
import task.NewOpportunityPortal.component.repository.StatusRepository;
import task.NewOpportunityPortal.component.service.StatusService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatusServiceImpl implements StatusService {

    private final StatusRepository repository;

    @Override
    public Long createStatus(Status status) {
        log.info("Create status: {}", status.toString());
        return repository.createStatus(status);
    }

    @Override
    @Cacheable("statuses")
    public Status getStatus(Long statusId) {
        log.info("Get status: {}", statusId);
        return repository.getStatus(statusId);
    }

    @Override
    @CacheEvict(value = "statuses")
    public void removeStatus(Long statusId) {
        log.info("Remove status: {}", statusId);
        repository.removeStatus(statusId);
    }

    @Override
    public List<Status> getAllStatuses() {
        log.info("Get all statuses");
        return repository.getAllStatuses();
    }
}
