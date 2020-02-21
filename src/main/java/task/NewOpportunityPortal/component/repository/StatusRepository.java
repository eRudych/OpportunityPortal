package task.NewOpportunityPortal.component.repository;

import task.NewOpportunityPortal.component.entity.Status;

import java.util.List;

public interface StatusRepository {

    Long createStatus(Status status);

    Status getStatus(Long statusId);

    void removeStatus(Long statusId);

    List<Status> getAllStatuses();
}
