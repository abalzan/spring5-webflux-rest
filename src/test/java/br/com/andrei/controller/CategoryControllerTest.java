package br.com.andrei.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.internal.hamcrest.HamcrestArgumentMatcher;
import org.mockito.internal.matchers.Any;
import org.reactivestreams.Publisher;
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
	public void setUp() throws Exception {
		categoryRepository = Mockito.mock(CategoryRepository.class);
		categoryController = new CategoryController(categoryRepository);

		webTestClient = WebTestClient.bindToController(categoryController).build();
	}

	@Test
	public void testListCategories() {
		// Behavior Driven Development
		BDDMockito.given(categoryRepository.findAll())
				.willReturn(Flux.just(Category.builder().description("Category1").build(),
						Category.builder().description("Category2").build()));

		webTestClient.get().uri("/api/v1/categories/").exchange().expectBodyList(Category.class).hasSize(2);
	}

	@Test
	public void testGetById() {
		BDDMockito.given(categoryRepository.findById("test123"))
				.willReturn(Mono.just(Category.builder().description("CategoryTest123").build()));

		webTestClient.get().uri("/api/v1/categories/test123").exchange().expectBody(Category.class);
	}

	@Test
	public void testCreateCategory() {
		BDDMockito.given(categoryRepository.saveAll(Mockito.any(Publisher.class)))
				.willReturn(Flux.just(Category.builder().build()));

		Mono<Category> catToSaveMono = Mono.just(Category.builder().description("Category Save Test").build());
	
		webTestClient.post()
					 .uri("/api/v1/categories")
					 .body(catToSaveMono, Category.class)
					 .exchange()
					 .expectStatus()
					 .isCreated();
	}

	@Test
	public void testUpdateCategory() {
		BDDMockito.given(categoryRepository.save(Mockito.any(Category.class)))
				.willReturn(Mono.just(Category.builder().build()));

		Mono<Category> catToUpdateMono = Mono.just(Category.builder().description("Category Update Test").build());

		webTestClient.put()
					 .uri("/api/v1/categories/notusedvalue")
					 .body(catToUpdateMono, Category.class)
					 .exchange()
					 .expectStatus()
					 .isOk();
	}

	@Test
	public void testDeleteCategory() {
		BDDMockito.given(categoryRepository.findById("test123"))
		.willReturn(Mono.just(Category.builder().description("CategoryTest123").build()));

		webTestClient.delete().uri("/api/v1/categories/test123").exchange().expectStatus().isOk();
	}
}
