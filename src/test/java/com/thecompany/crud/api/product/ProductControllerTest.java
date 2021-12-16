package com.thecompany.crud.api.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.thecompany.crud.adapter.ProductAdapter;
import com.thecompany.crud.common.exception.InternalException;
import com.thecompany.crud.common.exception.NotFoundException;
import com.thecompany.crud.model.Product;
import com.thecompany.crud.service.ProductService;

class ProductControllerTest {
	
	@InjectMocks
	ProductController productController;
	
	@Mock
	ProductService productService;
	
	@Mock
	ProductAdapter productAdapter;
	
	Long productId;
	String sku;
	ProductDto productDto;
	Product product;
	
	@BeforeEach
    void init() {
		MockitoAnnotations.openMocks(this);
		
		productController = new ProductController(
				productService, 
				productAdapter);
		
		productId = 1L;
		sku = "dummy";
				
		product = new Product();
		product.setId(productId);
		product.setSku(sku);
		product.setSku("dummy");
		product.setBrand("dummy");
		product.setName("dummy");
		product.setSize("dummy");
		product.setPrice(0);
		product.setPrincipalImage("dummy");
		product.setOtherImages("dummy");
		
		productDto = new ProductDto();
		productDto.setSku(product.getSku());
		productDto.setBrand(product.getBrand());
		productDto.setName(product.getName());
		productDto.setSize(product.getSize());
		productDto.setPrice(product.getPrice());
		productDto.setPrincipalImage(product.getPrincipalImage());
		productDto.setOtherImagesList(List.of(product.getOtherImages()));
	}
	
	@Test
	void whenFindAll_thenReturnProductDtoList() {
		List<Product> products = List.of(product);
		List<ProductDto> expectedProductDtoList = List.of(productDto);
		
		when(productAdapter.adapt(products)).thenReturn(expectedProductDtoList);
		when(productService.findAll()).thenReturn(products);
		
		List<ProductDto> actualProductDtoList = productController.findAll();
		
		assertEquals(expectedProductDtoList, actualProductDtoList);
	}
	
	@Test
	void whenFindBySku_thenReturnProductDto() throws NotFoundException {
		
		when(productAdapter.adapt(product)).thenReturn(productDto);
		when(productService.findBySku(sku)).thenReturn(product);
		
		ProductDto actualProductDto = productController.findBySku(sku);	
		assertEquals(productDto.getSku(), actualProductDto.getSku());
	}
	
	@Test
	void whenInsert_thenReturnProductDto() throws InternalException {
		
		when(productAdapter.adapt(productDto)).thenReturn(product);
		when(productService.insert(product)).thenReturn(product);
		when(productAdapter.adapt(product)).thenReturn(productDto);
	
		ProductDto actualProductDto = productController.insert(productDto);
		
		assertEquals(productDto.getSku(), actualProductDto.getSku());
	}
	
	@Test
	void whenUpdate_thenCallUpdateService() throws NotFoundException {
		
		when(productAdapter.adapt(productId, productDto)).thenReturn(product);
	
		productController.update(productId, productDto);
		
		verify(productService).update(product);
	}
	
	@Test
	void whenDelete_thenCallDeleteService() throws NotFoundException {
		
		productController.delete(productId);
		
		verify(productService).delete(productId);
	}

}
