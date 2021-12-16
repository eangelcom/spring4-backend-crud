package com.thecompany.crud.api.product;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.thecompany.crud.adapter.ProductAdapter;
import com.thecompany.crud.common.exception.InternalException;
import com.thecompany.crud.common.exception.NotFoundException;
import com.thecompany.crud.service.ProductService;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

	private final ProductService productService;
	private final ProductAdapter productAdapter;
	
	public ProductController(
			ProductService productService,
			ProductAdapter productAdapter) {

		this.productService = productService;
		this.productAdapter = productAdapter;
	}
	
	@GetMapping
	public List<ProductDto> findAll() {
		
		return productAdapter.adapt(
				productService.findAll()
				);
	}
	
	@GetMapping("/{sku}")
	public ProductDto findBySku(@PathVariable("sku") String sku) throws NotFoundException {
		
		return productAdapter.adapt(
				productService.findBySku(sku)
				);
	}
	
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public ProductDto insert(@Valid @RequestBody ProductDto productDto) throws InternalException {
	
		return productAdapter.adapt(
				productService.insert(
						productAdapter.adapt(productDto)
						)
				);
	}
	
	@PutMapping("/{id}")
	public void update(@PathVariable("id") Long id, @Valid @RequestBody ProductDto productDto) throws NotFoundException {
	
		productService.update(
				productAdapter.adapt(id, productDto)
				);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus( HttpStatus.ACCEPTED )
	public void delete(@PathVariable("id") Long id) throws NotFoundException {
		
		productService.delete(id);
	}
	
}
