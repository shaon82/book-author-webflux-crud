package com.shaon.declarative_crud_two.repository;

import com.shaon.declarative_crud_two.model.Author;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorRepository extends ReactiveCrudRepository<Author,Long> {
}
