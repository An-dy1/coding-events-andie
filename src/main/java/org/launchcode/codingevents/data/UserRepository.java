package org.launchcode.codingevents.data;

import org.launchcode.codingevents.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	// todo: reminder, why does this not have a return statement?
	User findByUsername(String username);
}
