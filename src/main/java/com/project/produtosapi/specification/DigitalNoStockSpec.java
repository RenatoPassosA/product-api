package com.project.produtosapi.specification;

import com.project.produtosapi.model.*;

public class DigitalNoStockSpec implements Specification<Product>{
	@Override
	public boolean isSatisfiedBy(Product product){
		if (!product.getDigitalProduct())
			return true;
		
		return product.getStock() == 0;
	}
}
