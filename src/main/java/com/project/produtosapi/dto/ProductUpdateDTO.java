package com.project.produtosapi.dto;

/**
 * Data Transfer Object (DTO) usado para atualização parcial de produtos.
 * Apenas os campos não nulos serão atualizados.
 */

public class ProductUpdateDTO  {
	
    private Double price;
    private String description;
    private Integer stock;
    private Integer maxDiscount;

	public Double getPrice() {
		return price;
	}
	public String getDescription() {
		return description;
	}
	public Integer getStock() {
		return stock;
	}
	public Integer getMaxDiscount() {
		return maxDiscount;
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
	public void setMaxDiscount(Integer maxDiscount) {
		this.maxDiscount = maxDiscount;
	}
}