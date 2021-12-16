package com.thecompany.crud.api.product;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductDto {
	
	private long id;
	
	@NotBlank(message = "SKU is required")
	private String sku;

	@NotBlank(message = "Name is required")
	@Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
	private String name;
	
	@NotBlank(message = "Brand is required")
	@Size(min = 3, max = 50, message = "Brand must be between 3 and 50 characters")
	private String brand;
	
	@NotBlank(message = "Size is required")
	private String size;
	
	@NotNull(message = "Price is required")
	@Min(value = 1, message = "Price should not be less than 1")
    @Max(value = 99999999, message = "Price should not be greater than 99,999,999")	
	private Integer price;
	
	@NotBlank(message = "URL of principal image is required")
	private String principalImage;

	private List<String> otherImagesList;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getPrincipalImage() {
		return principalImage;
	}

	public void setPrincipalImage(String principalImage) {
		this.principalImage = principalImage;
	}

	public List<String> getOtherImagesList() {
		return otherImagesList;
	}

	public void setOtherImagesList(List<String> otherImagesList) {
		this.otherImagesList = otherImagesList;
	}
	
}
