package br.com.andrei.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.andrei.domain.Vendor;
import br.com.andrei.repository.VendorRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class VendorControllerTest {

	WebTestClient webTestClient;
	VendorRepository vendorRepository;
	
	VendorController vendorController;
	
	@Before
	public void setUp() throws Exception {
		vendorRepository = Mockito.mock(VendorRepository.class);
		vendorController = new VendorController(vendorRepository);
		
		webTestClient = WebTestClient.bindToController(vendorController).build();
	}

	@Test
	public void testGetAllVendors() {
		BDDMockito.given(vendorRepository.findAll()).willReturn(Flux.just(
				Vendor.builder().firstName("First").lastName("Last").build(),
				Vendor.builder().firstName("FirstName").lastName("LastName").build()));
		
		webTestClient.get().uri("/api/v1/vendors/").exchange().expectBodyList(Vendor.class).hasSize(2);
	}

	@Test
	public void testGetVendorById() {
		BDDMockito.given(vendorRepository.findById("vendorTest")).willReturn(Mono.just(
				Vendor.builder().firstName("First").lastName("Last").build()));
		
		webTestClient.get().uri("/api/v1/vendors/vendorTest").exchange().expectBody(Vendor.class);
	}

}
