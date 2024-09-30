package com.ps.jpaTutorial.jpaTuts;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ps.jpaTutorial.jpaTuts.entities.ProductEntity;
import com.ps.jpaTutorial.jpaTuts.repositories.ProductRepository;

@SpringBootTest
class JpaTutorialApplicationTests {

	@Autowired
	ProductRepository productRepository;
	
	@Test
	void contextLoads() {
	}

	@Test
	void testRepository() {
		ProductEntity productEntity = ProductEntity.builder()
				.sku("COCOLA")
				.title("Sprite")
				.price(BigDecimal.valueOf(100.00))
				.quantity(10)
				.build();
		ProductEntity savedProductEntity  = productRepository.save(productEntity);
		System.out.println(savedProductEntity);
		
	} 
	
	@Test
	void getRepository() {
		List<ProductEntity> productEntity=productRepository.findAll();
		System.out.println("PS findAll :"+productEntity);
	}

	@Test
	void getRepositoryByTitle() {
		List<ProductEntity> productEntity=productRepository.findByTitle("Pasta");
		System.out.println("PS findByTitle :"+productEntity);
	}

	@Test
	void getRepositoryByCreatedAtAfter() {
		List<ProductEntity> productEntity=productRepository.findByCreatedAtAfter(LocalDateTime.of(2023, 8, 26, 0, 0, 0));
		System.out.println("PS findByCreatedAtAfter :"+productEntity);		
	}
	
	@Test
	void getRepositoryByTitleLike() {
		List<ProductEntity> productEntity=productRepository.findByTitleLike("%Cho%");
		System.out.println("PS findByTitleLike :"+productEntity);
	}
	
	@Test
	void getRepositoryByTitleContainingIgnoreCase() {
		List<ProductEntity> productEntity = productRepository.findByTitleContainingIgnoreCase("CHOco", null);
		System.out.println("PS findByTitleContainingIgnoreCase :"+productEntity);
	}
	
	@Test
	void getSingleFromRepository() {
		Optional<ProductEntity> productEntity = productRepository
				.findByTitleAndPrice("Nestle Choclate", BigDecimal.valueOf(123.36));
		productEntity.ifPresent(System.out::println);
	}
	
	
}
