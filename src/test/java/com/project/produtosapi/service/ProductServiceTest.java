package com.project.produtosapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.project.produtosapi.dto.*;
import com.project.produtosapi.exception.ProductNotFoundException;
import com.project.produtosapi.model.*;
import com.project.produtosapi.repository.*;



/* TESTES:
 * createDTOProduct
 * toDTO
 * updateProduct
 * findProductById sucess
 * findProductById not_find
 * getAll
 * deleteProduct
 * Validação de dados de entrada nos controllers (via @Valid e @RequestBody)
 */

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
	

	@Mock
    private ProductRepository productRepository;
	/*
	 * Aqui se eu não mockar o ProductRepository, vou estar testando a integração com o banco de dados, o que não é o objetivo dos testes unitários.
	 * Devo mockar o ProductRepository par simular o acesso ao banco de dados.
	 */

	 /*Por que mockar o repositório?
	  * Isolamento: Você está testando a lógica da sua camada de serviço (ProductService). Quando você chama productRepository.<algum metodo>, o objetivo do teste é
	  * verificar se a lógica do serviço está funcionando corretamente, e não se o repositório está funcionando corretamente (isso será testado em testes de integração).
	  * Controle sobre o comportamento: Quando você mocka o repositório, você tem controle total sobre o que o método retorna, permitindo que você simule
	  * tanto o caso em que o produto existe quanto o caso em que não existe (sem a necessidade de um banco de dados real). */

    private ProductService productService;

	@BeforeEach
    void setup() {
        productService = new ProductService(productRepository);
    }
	//antes de cada teste essa função será executada

	@Test //criação do Product a partir do ProductCreateDTO
	void createDTO()
	{
		ProductCreateDTO prodDTO = new ProductCreateDTO();
		prodDTO.setName("Teste");
        prodDTO.setPrice(10.0);
        prodDTO.setDescription("Descrição do teste");
        prodDTO.setCategory("test");
        prodDTO.setMaxDiscount(0);
        prodDTO.setStock(5);
        prodDTO.setWeight(1.0);
        prodDTO.setDigitalProduct(false);

		// Produto simulado que seria retornado pelo mock do repositório
    	Product create_dto_return = new Product(
        prodDTO.getName(),
        prodDTO.getPrice(),
        prodDTO.getDescription(),
        prodDTO.getCategory(),
        prodDTO.getMaxDiscount(),
        prodDTO.getStock(),
        prodDTO.getWeight(),
        prodDTO.getDigitalProduct()
    	);
    	create_dto_return.setId(1L); // Simula o ID gerado pelo banco de dados

		when(productRepository.save(any(Product.class))).thenReturn(create_dto_return);
		/*
		 * Mock do metodo save() para retornar o produto que seria salvo no banco de dados para que se possa fazer as assertions
		 * aqui eu meio que troco o retorno da função ProductCreateDTO para que ela retorno o create_dto_return para que eu possa fazer as assertions
		 */

		Product result = productService.createDTOProduct(prodDTO); //aqui a função com o retorno mockado
		
		assertNotNull(result.getId(), "id incorreto");
		assertEquals("Teste", result.getName(), "nome incorreto");
		assertEquals(10.0, result.getPrice(), 0.01, "preço incorreto");
		assertEquals("Descrição do teste", result.getDescription(), "descrição incorreta");
		assertEquals("test", result.getCategory(), "categoria incorreta");
		assertEquals(0, result.getMaxDiscount(), "valor incorreto");
		assertEquals(5, result.getStock(), "estoque incorreto");
		assertEquals(1.0, result.getWeight(), "peso incorreto");
		assertFalse(result.getDigitalProduct(), "produto digital incorreto");
	}

	@Test //transformação de um Product em DTO
	void toDTO()
	{
		Product prod = new Product("Notebook", 8000.00, "Gamer", "Tecnologia", 10, 1, 200.0, true);
		prod.setId(1L);

		ProductResponseDTO prod_DTO = productService.toDTO(prod);

		assertNotNull(prod_DTO.getId(), "id incorreto");
		assertEquals("Notebook", prod_DTO.getName(), "nome incorreto");
		assertEquals(8000.00, prod_DTO.getPrice(), "preço incorreto");
		assertEquals("Gamer", prod_DTO.getDescription(), "descrição incorreta");
		assertEquals(1, prod_DTO.getStock(), "estoque incorreto");
	}

	@Test //Atualização do Product com os dados DTO recebidos
	void updateProduct()
	{
		Product prod = new Product("Notebook", 8000.00, "Gamer", "Tecnologia", 10, 1, 200.0, false);
		prod.setId(10L);

		ProductUpdateDTO data_DTO = new ProductUpdateDTO();
		data_DTO.setPrice(prod.getPrice());
		data_DTO.setDescription(prod.getDescription());
		data_DTO.setStock(prod.getStock());
		data_DTO.setMaxDiscount(prod.getMaxDiscount());

		Product updatedProduct = new Product(
        prod.getName(),
        prod.getPrice(),
        prod.getDescription(),
        prod.getCategory(),
        prod.getMaxDiscount(),
        prod.getStock(),
        prod.getWeight(),
        prod.getDigitalProduct()
		);
		updatedProduct.setId(prod.getId());

		when(productRepository.findById(10L)).thenReturn(Optional.of(prod));
		when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

		Product result = productService.updateProduct(prod.getId(), data_DTO);

		assertNotNull(result);
		assertEquals(10L, result.getId(), "id incorreto");
		assertEquals(8000.00, result.getPrice(), "preço incorreto");
		assertEquals("Gamer", result.getDescription(), "descrição incorreta");
		assertEquals(1, result.getStock(), "estoque incorreto");
		assertEquals(10, result.getMaxDiscount(), "max disconut incorreto");
	}

	@Test
	void findProductById_Success()
	{
		Product prod = new Product("Notebook", 8000.00, "Gamer", "Tecnologia", 10, 1, 200.0, true);
		prod.setId(10L);

		when(productRepository.findById(10L)).thenReturn(Optional.of(prod));

		Product result = productService.findProductById(10L);

		assertNotNull(result);
		assertEquals("Notebook", result.getName(), "nome incorreto");
		assertEquals("Tecnologia", result.getCategory(), "categoria incorreto");
	}

	@Test
	void findProductById_NotFound()
	{
		when(productRepository.findById(10L)).thenReturn(Optional.empty());
		assertThrows(ProductNotFoundException.class, () -> {productService.findProductById(10L);});
	}

	/*
	 * assertThrows(ExcecaoEsperada.class, () -> {código que deve lançar a exceção});
	 * ExcecaoEsperada.class: O tipo de exceção que você espera que seja lançada durante a execução do código dentro do bloco.
	 * () -> { ... }: código que eu chamo e espero que a exceção seja lançada.
	 */

	@Test
	void getAll_Sucess()
	{
		Product prod1 = new Product("Notebook", 8000.0, "Gamer", "Tecnologia", 10, 1, 200.0, true);
        prod1.setId(1L);

        Product prod2 = new Product("Mouse", 150.0, "Periférico", "Tecnologia", 50, 1, 20.0, true);
        prod2.setId(2L);

		List<Product> productList = List.of(prod1, prod2);

		Mockito.when(productRepository.findAll()).thenReturn(productList);

		List<ProductResponseDTO> result = productService.getAll();

        assertEquals(2, result.size());
        assertEquals("Notebook", result.get(0).getName());
        assertEquals("Mouse", result.get(1).getName());
	}

	@Test
	void GetAll_EmptyList()
	{
		Mockito.when(productRepository.findAll()).thenReturn(Collections.emptyList());

		List<ProductResponseDTO> result = productService.getAll();
	
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}

	@Test
	void deleteProduct()
	{
		Product prod = new Product("Notebook", 8000.0, "Gamer", "Tecnologia", 10, 1, 200.0, true);
        prod.setId(1L);

		when(productRepository.findById(prod.getId())).thenReturn(Optional.of(prod));
    
   		productService.deleteProduct(prod.getId());
    
    	Mockito.verify(productRepository, Mockito.times(1)).delete(prod);
		/*
		 * Mockito.verify(...) serve para verificar se um método foi chamado no mock — e, opcionalmente, com quais argumentos e quantas vezes.
		 * Ele não testa o valor retornado, mas sim se o método foi invocado como esperado durante a execução do código.
		 */
	}

}