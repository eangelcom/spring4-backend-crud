package com.thecompany.crud.api.product;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thecompany.crud.CrudApplication;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CrudApplication.class)
@AutoConfigureMockMvc
class ProductControllerIntegrationTest {
	
	static final String API_PATH = "/api/v1/product";

	@Autowired
    MockMvc mockMvc;
	
	ProductDto productDto;
	
	@BeforeEach
    void init() throws Exception {
		
		productDto = new ProductDto();
		productDto.setSku("FAL-8406270");
		productDto.setName("500 Zapatilla Urbana Mujer");
		productDto.setBrand("SOME BRAND");
		productDto.setSize("37");
		productDto.setPrice(42990);
		productDto.setPrincipalImage("https://thecompany.scene7.com/is/image/thecompany/8406270_1");
		productDto.setOtherImagesList(List.of(
				"https://thecompany.scene7.com/is/image/thecompany/881952283_1", 
				"https://thecompany.scene7.com/is/image/thecompany/881952283_2"
		));
	
		insertProduct();
	}
	
	void insertProduct() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders
			      .post(API_PATH)
			      .content(new ObjectMapper().writeValueAsString(productDto))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON));			
	}
	
	@Test
	void whenGet_thenReturnProductInList() throws Exception {
		
		mockMvc.perform( MockMvcRequestBuilders
			      .get(API_PATH)
			      .accept(MediaType.APPLICATION_JSON))
			      .andDo(print())
			      .andExpect(status().isOk())
			      .andExpect(MockMvcResultMatchers.jsonPath("$.[0].sku").value(productDto.getSku()));		
	}
		
	@Test
	void whenGetSku_thenReturnProduct() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders
			      .get(API_PATH+"/{sku}", productDto.getSku())
			      .accept(MediaType.APPLICATION_JSON))
			      .andDo(print())
			      .andExpect(status().isOk())
			      .andExpect(MockMvcResultMatchers.jsonPath("$.sku").value(productDto.getSku()));
	}
	
	@Test
	void whenPut_thenReturnOk() throws Exception {

		productDto.setName("dummy name");
		
		mockMvc.perform( MockMvcRequestBuilders
			      .put(API_PATH+"/{id}", 1)
			      .content(new ObjectMapper().writeValueAsString(productDto))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());		
	}
	
	@Test
	void whenDelete_thenReturnOk() throws Exception {
		
		mockMvc.perform( MockMvcRequestBuilders
				.delete(API_PATH+"/{id}", 1) )
				.andExpect(status().isOk());		
	}
	
}
