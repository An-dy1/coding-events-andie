package org.launchcode.codingevents.data;

import org.launchcode.codingevents.models.Sponsor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SponsorRepository extends CrudRepository<Sponsor, Integer> {
}
