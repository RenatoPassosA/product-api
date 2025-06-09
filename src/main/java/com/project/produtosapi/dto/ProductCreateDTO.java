package com.project.produtosapi.dto;

import jakarta.validation.constraints.*;

public class ProductCreateDTO {

    @NotBlank(message = "O nome é obrigatório")
    private String name;
    @Positive(message = "O preço deve ser positivo")
    private Double price;
    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
    private String description;
    @NotBlank(message = "A categoria é obrigatória")
    private String category;
    @Positive(message = "O desconto máximo deve ser positivo")
    private Integer maxDiscount;
    @Positive(message = "O estoque deve ser positivo")
    private Integer stock;
    private Double weight;
    @NotNull(message = "É obrigatório informar se o produto é digital")
    private Boolean digitalProduct;

	
	public String getName() {
		return name;
	}
	public Double getPrice() {
		return price;
	}
	public String getDescription() {
		return description;
	}
	public String getCategory() {
		return category;
	}
	public Integer getMaxDiscount() {
		return maxDiscount;
	}
	public Integer getStock() {
		return stock;
	}
	public Double getWeight() {
		return weight;
	}
	public Boolean getDigitalProduct() {
		return digitalProduct;
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
	public void setCategory(String category) {
		this.category = category;
	}
	public void setMaxDiscount(Integer maxDiscount) {
		this.maxDiscount = maxDiscount;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public void setDigitalProduct(Boolean digitalProduct) {
		this.digitalProduct = digitalProduct;
	}
}