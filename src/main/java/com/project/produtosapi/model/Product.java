package com.project.produtosapi.model; // é um endereçamento e agrupador para as classes

import jakarta.persistence.*;  // traz as anotações para manusear o banco de dados
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
public class Product
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long			id;
	@NotBlank
	private String			name;
	@Positive
	private Double			price;
	@Column(length = 500)
	private String			description;
	@NotBlank
	private String			category;
	@Positive
	private Integer			maxDiscount;
	@Positive
	private Integer			stock;
	private Double			weight;
	@NotBlank
	private	Boolean			digitalProduct;
	private LocalDateTime	creationDate;

	protected Product() {
    }

	public Product(String name, Double price, String description, String category, Integer maxDiscount, Integer stock, Double weight){
		this();
		this.name = name;
		this.price = price;
		this.description = description;
		this.category = category;
		this.maxDiscount = maxDiscount;
		this.stock = stock;
		this.weight = weight;
		this.creationDate = LocalDateTime.now();
	}

	public Long getId(){
		return id;
	}

	public String getName(){
		return name;
	}

	public Double getPrice(){
		return price;
	}

	public String getDescription(){
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

	public LocalDateTime getCreationDate(){
		return creationDate;
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
