package br.com.andrei.controller;

import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/api/v1/vendors")
	public Mono<Void> createVendor(@RequestBody Publisher<Vendor> newVendor){
		return vendorRepository.saveAll(newVendor).then();
	}
	
	@PutMapping("/api/v1/vendors/{id}")
	public Mono<Vendor> updateCategory(@PathVariable String id, @RequestBody Vendor vendor){
		vendor.setId(id);
		return vendorRepository.save(vendor);
	}
	
	@DeleteMapping("/api/v1/vendors/{id}")
	public Mono<Void> deleteCategory(@PathVariable String id){
		return vendorRepository.deleteById(id);
	}
	

}
