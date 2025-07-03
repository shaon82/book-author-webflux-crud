package com.shaon.declarative_crud_two.repository;

import com.shaon.declarative_crud_two.model.Books;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends ReactiveCrudRepository<Books,Long> {
}
