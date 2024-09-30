package com.ps.jpaTutorial.jpaTuts.repositories;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ps.jpaTutorial.jpaTuts.entities.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

	List<ProductEntity> findByTitle(String title);

	List<ProductEntity> findByCreatedAtAfter(LocalDateTime createdAt);

	List<ProductEntity> findByTitleLike(String title);
	
	//List<ProductEntity> findBycreatedAtAfter(String string);
	//Optional<ProductEntity> findByTitleAndPrice(String title, BigDecimal price);
	 List<ProductEntity> findByTitleContainingIgnoreCase(String title,  Pageable pageable);
	 
  @Query("select e.title, e.price from ProductEntity e where e.title=:title and e.price=:price")
  Optional<ProductEntity> findByTitleAndPrice(String title, BigDecimal price);

List<ProductEntity> findByTitleOrderByPrice(String title);

List<ProductEntity> findByOrderByPrice();

List<ProductEntity> findBy(Sort sort);

Page<ProductEntity> findAll( Pageable pageable);
}
