package org.launchcode.codingevents.data;

import org.launchcode.codingevents.models.Club;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends CrudRepository<Club, Integer> {
}
