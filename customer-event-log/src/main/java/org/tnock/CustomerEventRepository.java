package org.tnock;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "events", path = "events")
public interface CustomerEventRepository
        extends PagingAndSortingRepository<CustomerEvent, Long> {


}
