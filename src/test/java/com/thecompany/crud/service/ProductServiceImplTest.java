package com.thecompany.crud.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.thecompany.crud.api.product.ProductDto;
import com.thecompany.crud.common.exception.InternalException;
import com.thecompany.crud.common.exception.NotFoundException;
import com.thecompany.crud.dao.ProductRepository;
import com.thecompany.crud.model.Product;

class ProductServiceImplTest {
	
	@InjectMocks
	ProductServiceImpl productServiceImpl;
	
	@Mock
	ProductRepository productRepository;
	
	Long productId;
	String sku;
	ProductDto productDto;
	Product product;
	
	@BeforeEach
    void init() {
		MockitoAnnotations.openMocks(this);
		
		productServiceImpl = new ProductServiceImpl(productRepository);
		
		productId = 1L;
		sku = "dummy";
				
		productDto = new ProductDto();
		productDto.setSku(sku);
		
		product = new Product();
		product.setSku(sku);
	}
	
	@Test
	void whenFindAll_thenReturnProducts() {
		List<Product> expectedProducts = List.of(product);
		
		when(productRepository.findAll()).thenReturn(expectedProducts);
		
		List<Product> actualProducts = productServiceImpl.findAll();
		
		assertEquals(expectedProducts, actualProducts);
	}
	
	@Test
	void givenExistingProduct_whenFindBySku_thenReturnProduct() throws NotFoundException {
		Optional<Product> expectedProduct = Optional.of(product);
		
		when(productRepository.findBySku(sku)).thenReturn(expectedProduct);
		
		Product actualProduct = productServiceImpl.findBySku(sku);	
		
		assertEquals(expectedProduct.get().getSku(), actualProduct.getSku());
	}
	
	@Test
	void givenNotExistingProduct_whenFindBySku_thenThrowNotFoundException() {
		
		ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> {
			productServiceImpl.findBySku(sku);
        });
		
		assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
	}
	
	@Test
	void whenInsert_thenReturnProduct() throws InternalException {
		Product expectedProduct = product;
		
		when(productRepository.findBySku(product.getSku())).thenReturn(Optional.empty());
		when(productRepository.save(product)).thenReturn(expectedProduct);
	
		Product actualProduct = productServiceImpl.insert(product);
		
		assertEquals(expectedProduct.getSku(), actualProduct.getSku());
	}
	
	@Test
	void givenExistingProduct_whenInsert_thenThrowInternalException() {
		Optional<Product> existingProduct = Optional.of(product);
		
		when(productRepository.findBySku(product.getSku())).thenReturn(existingProduct);
		
		ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> {
			productServiceImpl.insert(product);
        });
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, ex.getStatus());
	}
	
	@Test
	void whenUpdate_thenCallRepositorySave() throws NotFoundException {
		
		when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
	
		productServiceImpl.update(product);
		
		verify(productRepository).save(product);
	}
	
	@Test
	void givenNotExistingProduct_whenUpdate_thenThrowNotFoundException() {
		
		ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> {
			productServiceImpl.update(product);
        });
		
		assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
	}
	
	@Test
	void whenDelete_thenCallRepositoryDelete() throws NotFoundException {
		Optional<Product> expectedProduct = Optional.of(product);
		
		when(productRepository.findById(productId)).thenReturn(expectedProduct);
	
		productServiceImpl.delete(productId);
		
		verify(productRepository).delete(expectedProduct.get());
	}
	
	@Test
	void givenNotExistingProduct_whenDelete_thenThrowNotFoundException() {
		
		ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> {
			productServiceImpl.delete(productId);
        });
		
		assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
	}
	
}
