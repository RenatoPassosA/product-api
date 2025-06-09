package com.project.produtosapi.specification;

import com.project.produtosapi.model.*;
import java.util.Arrays;
import java.util.List;

public class DescriptionForbbidenWordsSpec implements Specification<Product> {

	private final List<String> prohibitedWords = Arrays.asList("violento", "ilegal", "explosivo", "arma", "drogas");

	@Override
	public boolean isSatisfiedBy(Product product){
		if (product.getDescription() == null)
            return true;
		
		String descriptionLower = product.getDescription().toLowerCase();
		
		for (String word : prohibitedWords) {
            if (descriptionLower.contains(word)) {
                return false;
            }
        }
        return true;
	}
}
