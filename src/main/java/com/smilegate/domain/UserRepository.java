package com.smilegate.domain;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserData, Long> {
	UserData findByUserId(String userId);
}
