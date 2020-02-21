package task.NewOpportunityPortal.component.service;

import task.NewOpportunityPortal.component.entity.Status;

import java.util.List;

public interface StatusService {

    Long createStatus(Status status);

    Status getStatus(Long statusId);

    void removeStatus(Long statusId);

    List<Status> getAllStatuses();
}
