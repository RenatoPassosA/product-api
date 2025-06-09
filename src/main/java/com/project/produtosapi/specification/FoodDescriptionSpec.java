package com.project.produtosapi.specification;

import com.project.produtosapi.model.*;

public class FoodDescriptionSpec implements Specification<Product> {
	@Override
	public boolean isSatisfiedBy(Product product){
		if (!"food".equals(product.getCategory()))
        	return true;
    	
		return product.getDescription() != null && !product.getDescription().trim().isEmpty();
	}
}
