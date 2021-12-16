package com.thecompany.crud.adapter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.thecompany.crud.api.product.ProductDto;
import com.thecompany.crud.common.util.BeanUtil;
import com.thecompany.crud.model.Product;

@Component
public class ProductAdapter {
	
	private final BeanUtil beanUtil;

	public ProductAdapter(BeanUtil beanUtil) {

		this.beanUtil = beanUtil;
	}

	public List<ProductDto> adapt(List<Product> products) {
		
		return products.stream().map(product -> {
					ProductDto dto = new ProductDto();
					dto = (ProductDto) beanUtil.copyProperties(dto, product);
					dto.setOtherImagesList(beanUtil.csvToList(product.getOtherImages()));
					return dto;
				}).collect(Collectors.toList());
	}

	public Product adapt(ProductDto productDto) {
		Product product = new Product();
		product = (Product) beanUtil.copyProperties(product, productDto);
		product.setOtherImages(beanUtil.listToCsv(productDto.getOtherImagesList()));
		return product;
	}
	
	public Product adapt(Long id, ProductDto productDto) {
	
		productDto.setId(id);
		return adapt(productDto);
	}
	
	public ProductDto adapt(Product product) {
		ProductDto productDto = new ProductDto();
		productDto = (ProductDto) beanUtil.copyProperties(productDto, product);
		productDto.setOtherImagesList(beanUtil.csvToList(product.getOtherImages()));
		return productDto;
	}

}
