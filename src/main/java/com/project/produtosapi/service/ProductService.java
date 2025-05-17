package com.project.produtosapi.service;

import org.springframework.stereotype.Service;

import com.project.produtosapi.model.Product;
import com.project.produtosapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service //marca a classe como compenente do spring e indica que terá regras de negocio
public class ProductService {
    
    private final ProductRepository repository; //declara uma dependencia imutavel

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

	public Product createProduct(Product product) {
		if (product.getPrice() > 1000 && product.getDescription() == null){
			throw new BusinessException("Produtos acima de 1000 devem ter descrição");
		}
		
		
		return (repository.save(product));
	}

   // /*public Product findProductById(Long id) { /*...*/}

   // public Product updateProduct(Long id, ProductUpdateDTO dto) { /*...*/ }

   // public void deleteProduct(Long id) { /*...*/ }

    // Método interno/exemplo
   // private void validateProductPrice(Product product) { /*...*/ }*/
}
