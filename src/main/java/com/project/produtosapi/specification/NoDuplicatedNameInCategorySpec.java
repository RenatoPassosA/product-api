package com.project.produtosapi.specification;

import com.project.produtosapi.model.*;
import com.project.produtosapi.repository.*;

public class NoDuplicatedNameInCategorySpec implements Specification<Product> {

	private final ProductRepository productRepository;

	public NoDuplicatedNameInCategorySpec (ProductRepository productRepository){
		this.productRepository = productRepository;;
	}

	@Override
	public boolean isSatisfiedBy(Product product){
		return productRepository.findByNameAndCategory(product.getName(), product.getCategory()).isEmpty();
	}
}
