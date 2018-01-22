package br.com.andrei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.andrei.domain.Category;
import br.com.andrei.repository.CategoryRepository;
import br.com.andrei.repository.VendorRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private VendorRepository vendorRepository;
	

	@GetMapping("/api/v1/categories")
	public Flux<Category> list(){
		return categoryRepository.findAll();
	}
	
	@GetMapping("/api/v1/categories/{id}")
	public Mono<Category> getById(@PathVariable String id){
		return categoryRepository.findById(id);
	} 
}
