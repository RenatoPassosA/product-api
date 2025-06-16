package com.project.produtosapi.controller;

import org.springframework.web.bind.annotation.*;

import com.project.produtosapi.model.*;
import com.project.produtosapi.service.*;
import com.project.produtosapi.dto.*;

import jakarta.validation.Valid;
import java.util.List;


@RequestMapping("/products")
@RestController
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
    	this.productService = productService;
	}

	@PostMapping
	public	ProductResponseDTO createProduct(@Valid @RequestBody ProductCreateDTO product)
	{
		ProductResponseDTO response;
		Product		prod;

		prod = productService.createDTOProduct(product);
		response = productService.toDTO(prod);
		return(response);
	}
	/*
	 * @PostMapping	Diz ao Spring que esse método responde a requisições HTTP POST
	 * @RequestBody para receber o JSON enviado pelo cliente
	 * @Valid Ativa as validações declaradas nas anotações do DTO
	 */

	@GetMapping("/{id}")
	public	ProductResponseDTO getProduct(@PathVariable Long id)
	{
		ProductResponseDTO response;
		Product		prod;

		prod = productService.findProductById(id);
		response = productService.toDTO(prod);
		return(response);
	}
	/*
	 * @GetMapping("/{id}") //Esse método responde a requisições GET na rota /produtos/{id}
	 * @PathVariable Captura o número que vem na URL e injeta no seu método como argumento
	 */
	
	@PutMapping("/{id}")
	public ProductResponseDTO updateProduct(@PathVariable Long id, @Valid @RequestBody  ProductUpdateDTO dto)
	{
		Product		prod;
		ProductResponseDTO response;

		prod = productService.updateProduct(id, dto);
		response = productService.toDTO(prod);
		return (response);
	}
	/*
	 * @PutMapping("/{id}")	Diz que esse método responde a requisições HTTP PUT na rota /produtos/{id}
	 */

	@GetMapping
	public List<ProductResponseDTO> getAll()
	{
		List<ProductResponseDTO> products = productService.getAll();
		return (products);
	}

	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable Long id)
	{
		productService.deleteProduct(id);
		return ;
	}
}
