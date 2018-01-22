package br.com.andrei.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Document
@Builder
@AllArgsConstructor
public class Vendor {

	@Id
	private String id;
	
	private String firstName;
	
	private String lastName;
}
