package task.NewOpportunityPortal.repository;

import task.NewOpportunityPortal.entity.Status;

import java.util.List;

public interface StatusRepository {

    Long createStatus(Status status);

    Status getStatus(Long statusId);

    boolean removeStatus(Long statusId);

    List<Status> getAllStatuses();
}
