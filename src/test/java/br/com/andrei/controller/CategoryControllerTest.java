package br.com.andrei.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.andrei.domain.Category;
import br.com.andrei.repository.CategoryRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CategoryControllerTest {

	WebTestClient webTestClient;
	CategoryRepository categoryRepository;
	CategoryController categoryController;
	
	@Before
	public void setUp() throws Exception{
		categoryRepository = Mockito.mock(CategoryRepository.class);
		categoryController = new CategoryController(categoryRepository);
		
		webTestClient = WebTestClient.bindToController(categoryController).build();
	}
	
	@Test
	public void testListCategories() {
		//Behavior Driven Development
		BDDMockito.given(categoryRepository.findAll()).willReturn(Flux.just(
				Category.builder().description("Category1").build(),
				Category.builder().description("Category2").build()));
		
		webTestClient.get().uri("/api/v1/categories/").exchange().expectBodyList(Category.class).hasSize(2);
	}
	
	@Test
	public void testGetById() {
		BDDMockito.given(categoryRepository.findById("test123")).willReturn(Mono.just(
				Category.builder().description("CategoryTest123").build()));
		
		webTestClient.get().uri("/api/v1/categories/test123").exchange().expectBody(Category.class);
	}

}
