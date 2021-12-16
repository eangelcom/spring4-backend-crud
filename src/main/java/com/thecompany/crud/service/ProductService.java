package com.thecompany.crud.service;

import java.util.List;

import com.thecompany.crud.common.exception.InternalException;
import com.thecompany.crud.common.exception.NotFoundException;
import com.thecompany.crud.model.Product;

public interface ProductService {

	List<Product> findAll();

	Product findBySku(String sku) throws NotFoundException;

	Product insert(Product product) throws InternalException;

	void update(Product product) throws NotFoundException;

	void delete(Long id) throws NotFoundException;

}
