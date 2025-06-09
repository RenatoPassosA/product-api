package com.project.produtosapi.specification;

import com.project.produtosapi.model.*;

public class DigitalNoWeightSpec implements Specification<Product>{
	@Override
	public boolean isSatisfiedBy(Product product){
		return !product.getDigitalProduct() || product.getWeight() == 0;
	}	
}
