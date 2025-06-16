package com.project.produtosapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.produtosapi.dto.ProductCreateDTO;
import com.project.produtosapi.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerValidationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createProduct_InvalidInputError() throws Exception {
        
        ProductCreateDTO dto = new ProductCreateDTO();
        dto.setName(""); 
        dto.setPrice(-10.0); 
        dto.setCategory(""); 
        dto.setDescription("Teste");
        dto.setStock(-1); 
        dto.setWeight(-2.0); 
        dto.setMaxDiscount(0);
        dto.setDigitalProduct(false);

        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("O nome é obrigatório")))
                .andExpect(content().string(containsString("O preço deve ser positivo")))
				.andExpect(content().string(containsString( "A categoria é obrigatória")))
				.andExpect(content().string(containsString("O estoque deve ser positivo")))
				.andExpect(content().string(containsString("O desconto máximo deve ser positivo")))
				.andExpect(content().string(containsString("O nome é obrigatório")));
    }
}