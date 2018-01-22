package br.com.andrei.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import br.com.andrei.domain.Vendor;

public interface VendorRepository extends ReactiveMongoRepository<Vendor, String>{

}
