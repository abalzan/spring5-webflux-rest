package br.com.andrei.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.andrei.domain.Category;
import br.com.andrei.repository.CategoryRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CategoryController {

	private CategoryRepository categoryRepository;
	
	public CategoryController(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@GetMapping("/api/v1/categories")
	public Flux<Category> list(){
		return categoryRepository.findAll();
	}
	
	@GetMapping("/api/v1/categories/{id}")
	public Mono<Category> getById(@PathVariable String id){
		return categoryRepository.findById(id);
	} 
}
