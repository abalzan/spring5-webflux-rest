package br.com.andrei.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.andrei.domain.Vendor;
import br.com.andrei.repository.VendorRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class VendorController {

	private VendorRepository vendorRepository;

	public VendorController(VendorRepository vendorRepository) {
		this.vendorRepository = vendorRepository;
	}
	
	@GetMapping("/api/v1/vendors")
	public Flux<Vendor> getAllVendors(){
		return vendorRepository.findAll();
	}
	
	@GetMapping("/api/v1/vendors/{id}")
	public Mono<Vendor> getVendorById(@PathVariable String id){
		return vendorRepository.findById(id);
	}

}
