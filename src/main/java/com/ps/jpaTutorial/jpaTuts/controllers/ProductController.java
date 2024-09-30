package com.ps.jpaTutorial.jpaTuts.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ps.jpaTutorial.jpaTuts.entities.ProductEntity;
import com.ps.jpaTutorial.jpaTuts.repositories.ProductRepository;

@RestController
@RequestMapping(path = "/products")
public class ProductController {
	
	private final ProductRepository productRepository;
private final int PAGE_SIZE = 5;
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    @GetMapping
    public List<ProductEntity> getAllProducts(){
    	System.out.println("ps Track1");
    	return productRepository.findByTitleOrderByPrice("Pepsi");
    }
        
    @GetMapping("/by-sortExample")
    public List<ProductEntity> getAllProductSorted(@RequestParam(defaultValue = "id") String sortBy){
    	return productRepository.findBy(Sort.by(Sort.Direction.DESC, sortBy, "price", "quantity"));
    }
    
    @GetMapping("/by-price")
    public List<ProductEntity> getAllProductsByPrice(){
    	System.out.println("ps Track1");
    	return productRepository.findByOrderByPrice();
    }
    
    @GetMapping("/by-paginationExample")
    public List<ProductEntity> getAllProductSorted(@RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "0") Integer pageNumber){
    	Pageable pageable = PageRequest.of(pageNumber,
    			PAGE_SIZE,
    			Sort.by(sortBy));
    	
    	//http://localhost:8080/products/by-paginationExample?sortBy=sku&pageNumber=2
    	return productRepository.findAll(pageable).getContent();
    }
    
    
}
