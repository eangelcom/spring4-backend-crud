package com.thecompany.crud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thecompany.crud.common.exception.InternalException;
import com.thecompany.crud.common.exception.NotFoundException;
import com.thecompany.crud.dao.ProductRepository;
import com.thecompany.crud.model.Product;

@Service
public class ProductServiceImpl implements ProductService {
	
	private final ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {

		this.productRepository = productRepository;
	}

	@Override
	public List<Product> findAll() {

		return productRepository.findAll();
	}

	@Override
	public Product findBySku(String sku) throws NotFoundException {
		var savedProduct = productRepository.findBySku(sku);
		if(savedProduct.isPresent()) {
			return savedProduct.get();
		} else {
			throw new NotFoundException("Not found SKU=" + sku);
		}	
	}

	@Override
	public Product insert(Product product) throws InternalException {
		var savedProduct = productRepository.findBySku(product.getSku());
		if(savedProduct.isPresent()) {
			throw new InternalException("Already exists SKU=" + product.getSku());
		} else {
			return productRepository.save(product);
		}	
	}

	@Override
	public void update(Product product) throws NotFoundException {		
		var savedProduct = productRepository.findById(product.getId());
		if(savedProduct.isPresent()) {
			productRepository.save(product);
		} else {
			throw new NotFoundException("Not found id=" + product.getId());
		}		
	}

	@Override
	public void delete(Long id) throws NotFoundException {
		var savedProduct = productRepository.findById(id);
		if(savedProduct.isPresent()) {
			productRepository.delete(savedProduct.get());
		} else {
			throw new NotFoundException("Not found id=" + id);
		}	
	}

}
