package com.project.produtosapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.produtosapi.model.Product;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>{
	List<Product> findByName(String name);
	List<Product> findByNameAndCategory(String name, String category);
}