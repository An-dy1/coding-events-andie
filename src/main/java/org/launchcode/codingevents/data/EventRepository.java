package org.launchcode.codingevents.data;

import org.launchcode.codingevents.models.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// we don't have to have a class that implements this EventRepository although you cannot create an instance of this interface (or any interfaces)
// because CrudRepository is special because it's so boilerplate, and Spring Boot in-memory on the fly will create a class that implements the EventRepository
// then Spring Boot will make sure it can be autowired into the field holding it
@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {
}
