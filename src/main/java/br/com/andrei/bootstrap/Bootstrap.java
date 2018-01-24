package br.com.andrei.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.andrei.domain.Category;
import br.com.andrei.domain.Vendor;
import br.com.andrei.repository.CategoryRepository;
import br.com.andrei.repository.VendorRepository;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private VendorRepository vendorRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		categoryRepository.deleteAll().block();
		if(categoryRepository.count().block() ==0) {
			createCategory();
		}
		
		vendorRepository.deleteAll().block();
		if(vendorRepository.count().block() == 0 ) {
			createVendor();
		}
	}

	private void createVendor() {
		
		vendorRepository.save(Vendor.builder().firstName("Paul").lastName("Allen").build()).block();
		vendorRepository.save(Vendor.builder().firstName("John").lastName("Doe").build()).block();
		vendorRepository.save(Vendor.builder().firstName("Catie").lastName("Window").build()).block();
		vendorRepository.save(Vendor.builder().firstName("Rutie").lastName("Smith").build()).block();
		vendorRepository.save(Vendor.builder().firstName("Kevin").lastName("Yarn").build()).block();
		
		System.out.println("We have " + vendorRepository.count().block() +" vendor in our database!!!!");
		
	}

	private void createCategory() {
//		NORMAL WAY TO INSERT NEW ENTRIES
//		Category c2 = new Category();
//		c2.setDescription("Fruits");
//		categoryRepository.save(c2).block();

//		USING @Builder annotation from lombok in domain classes.
		categoryRepository.save(Category.builder().description("Fruits").build()).block();
		categoryRepository.save(Category.builder().description("Nuts").build()).block();
		categoryRepository.save(Category.builder().description("Breads").build()).block();
		categoryRepository.save(Category.builder().description("Meats").build()).block();
		categoryRepository.save(Category.builder().description("Eggs").build()).block();
		
		System.out.println("We have " + categoryRepository.count().block() +" Categories in our database!!!!");
	}
	
}
