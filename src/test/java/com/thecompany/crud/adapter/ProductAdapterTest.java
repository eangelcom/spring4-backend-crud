package com.thecompany.crud.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.thecompany.crud.api.product.ProductDto;
import com.thecompany.crud.common.util.BeanUtil;
import com.thecompany.crud.model.Product;

class ProductAdapterTest {
	
	@InjectMocks
	ProductAdapter productAdapter;
	
	@Mock
	BeanUtil beanUtil;
	
	Long productId;
	String sku;
	ProductDto productDto;
	Product product;
	
	@BeforeEach
    void init() {
		MockitoAnnotations.openMocks(this);
		
		productAdapter = new ProductAdapter(beanUtil);
		
		productId = 1L;
		sku = "dummy";
				
		productDto = new ProductDto();
		productDto.setSku(sku);
		
		product = new Product();
		product.setSku(sku);
	}
	
	@Test
	void givenProducts_whenAdapt_thenReturnProductDtoList() {
		List<Product> products = List.of(product);
		
		when(beanUtil.copyProperties(any(), any())).thenReturn(productDto);
	
		List<ProductDto> actualProductDtoList = productAdapter.adapt(products);
		
		assertEquals(products.get(0).getSku(), actualProductDtoList.get(0).getSku());
	}
	
	@Test
	void givenProductDto_whenAdapt_thenReturnProduct() {
		
		when(beanUtil.copyProperties(any(), any())).thenReturn(product);
	
		Product actualProduct = productAdapter.adapt(productDto);
		
		assertEquals(productDto.getSku(), actualProduct.getSku());
	}
	
	@Test
	void givenProductDtoAndId_whenAdapt_thenReturnProduct() {
		
		when(beanUtil.copyProperties(any(), any())).thenReturn(product);
	
		Product actualProduct = productAdapter.adapt(productId, productDto);
		
		assertEquals(productDto.getSku(), actualProduct.getSku());
	}
	
	@Test
	void givenProduct_whenAdapt_thenReturnProductDto() {
		
		when(beanUtil.copyProperties(any(), any())).thenReturn(productDto);
	
		ProductDto actualProductDto = productAdapter.adapt(product);
		
		assertEquals(product.getSku(), actualProductDto.getSku());
	}

}
