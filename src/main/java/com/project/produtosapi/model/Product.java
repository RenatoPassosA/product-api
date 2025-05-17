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
	private LocalDateTime	creationDate;

	protected Product() {
    }

	public Product(String name, Double price, String description){
		this();
		this.name = name;
		this.price = price;
		this.description = description;
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

}
