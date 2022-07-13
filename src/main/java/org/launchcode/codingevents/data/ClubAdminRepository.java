package org.launchcode.codingevents.data;

import org.launchcode.codingevents.models.ClubAdmin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubAdminRepository extends CrudRepository<ClubAdmin, Integer> {
}
