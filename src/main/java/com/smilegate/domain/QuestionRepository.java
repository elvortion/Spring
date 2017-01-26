package com.smilegate.domain;

import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Long> {
	Question findByWriter(UserData writer);
}
