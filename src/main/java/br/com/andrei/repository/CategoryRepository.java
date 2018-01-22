package br.com.andrei.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import br.com.andrei.domain.Category;

public interface CategoryRepository extends ReactiveMongoRepository<Category, String>{

}
