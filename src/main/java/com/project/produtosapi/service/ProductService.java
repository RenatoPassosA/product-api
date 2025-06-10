package com.project.produtosapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.produtosapi.model.Product;
import com.project.produtosapi.repository.ProductRepository;
import com.project.produtosapi.specification.*;
import com.project.produtosapi.dto.*;
import com.project.produtosapi.exception.*;


@Service //marca a classe como compenente do spring e indica que terá regras de negocio
public class ProductService {


	private final ProductRepository productRepository;
	private final Specification<Product> rule1;
	private final Specification<Product> rule2;
	private final Specification<Product> rule3;
	private final Specification<Product> rule4;
	private final Specification<Product> rule5;
	
	public ProductService(ProductRepository productRepository) {
    	this.productRepository = productRepository;
		this.rule1 = new DescriptionForbbidenWordsSpec();
		this.rule2 = new DigitalNoStockSpec();
		this.rule3 = new DigitalNoWeightSpec();
		this.rule4 = new FoodDescriptionSpec();
		this.rule5 = new NoDuplicatedNameInCategorySpec(productRepository);
	}

	// === Validações de Regras ===
	public void checkCreationRules(Product product)
	{
		if (!rule1.isSatisfiedBy(product))
			throw new BusinessRuleException("Descrição ofensiva não permitida");
		if (!rule2.isSatisfiedBy(product))
			throw new BusinessRuleException("Produto digital não deve ter estoque");
		if (!rule3.isSatisfiedBy(product))
			throw new BusinessRuleException("Produto digital não deve ter peso");
		if (!rule4.isSatisfiedBy(product))
			throw new BusinessRuleException("Produto alimentício deve ter uma descrição");
		if (!rule5.isSatisfiedBy(product))
			throw new BusinessRuleException("Não podem haver nomes duplicados");
	}

	public void checkUpdateRules(Product product)
	{
		if (!rule1.isSatisfiedBy(product))
			throw new BusinessRuleException("Descrição ofensiva não permitida");
		if (!rule2.isSatisfiedBy(product))
			throw new BusinessRuleException("Produto digital não deve ter estoque");
		if (!rule3.isSatisfiedBy(product))
			throw new BusinessRuleException("Produto digital não deve ter peso");
		if (!rule4.isSatisfiedBy(product))
			throw new BusinessRuleException("Produto alimentício deve ter uma descrição");
	}

	// === CRUD ===
	public Product createDTOProduct(ProductCreateDTO dto) {
		Product new_product = new Product(
			dto.getName(),
			dto.getPrice(),
			dto.getDescription(),
			dto.getCategory(),
			dto.getMaxDiscount(),
			dto.getStock(),
			dto.getWeight(),
			dto.getDigitalProduct());
		checkCreationRules(new_product);
		return (productRepository.save(new_product));
	}

	public Product findProductById(Long id) {

		Optional<Product> find = productRepository.findById(id);

		if (find.isPresent())
			return (find.get());
		else
			throw new ProductNotFoundException(id);
    }

	public Product updateProduct(Long id, ProductUpdateDTO data) {
		Product product = this.findProductById(id); //aqui a exceção interrompe o resto da função, se for lançada

		if (data.getDescription() != null)
			product.setDescription(data.getDescription());
		if (data.getMaxDiscount() != null)
			product.setMaxDiscount(data.getMaxDiscount());
		if (data.getPrice() != null)
			product.setPrice(data.getPrice());
		if (data.getStock() != null)
			product.setStock(data.getStock());
		
		checkUpdateRules(product);
		return (productRepository.save(product));
		/* aqui as modificações são feitas no product em cada set e após isso as regras de negócio são novamente reavaliadas.
		 * porem só há o save no banco de dados após todas as regras serem validadas, não permitindo assim valores fora da regra dentro do banco de dados
		 */
	}

	public List<ProductResponseDTO> getAll()
	{
		List<Product> products;
		List<ProductResponseDTO> response = new ArrayList<>();;
		int	index = -1;

		products = productRepository.findAll();
		while(++index < products.size())
			response.add(toDTO(products.get(index)));
		return (response);
	}

	public void deleteProduct(Long id){
		Product product = this.findProductById(id);
		productRepository.delete(product);
		return ;
	}

	// === Conversão ===
	public ProductResponseDTO toDTO(Product product) {
	
		ProductResponseDTO dto = new ProductResponseDTO();
		dto.setId(product.getId());
		dto.setName(product.getName());
		dto.setPrice(product.getPrice());
		dto.setDescription(product.getDescription());
		dto.setStock(product.getStock());

		return (dto);
	}
}
