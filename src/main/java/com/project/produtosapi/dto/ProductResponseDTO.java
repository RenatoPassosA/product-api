package com.project.produtosapi.dto;


/*
 * essa classe serve para controlar quais dados da entidade Product são enviados ao cliente, evitando
 * export alguns dados internos e tambem é possivel formatar ou transformar campos, se necessário.
 */

public class ProductResponseDTO {
	
	private Long id;
    private String name;
    private Double price;
    private String description;
    private Integer stock;

	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Double getPrice() {
		return price;
	}
	public String getDescription() {
		return description;
	}
	public Integer getStock() {
		return stock;
	}

}
