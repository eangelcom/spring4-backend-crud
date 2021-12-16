package com.thecompany.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "sku")
	private String sku;

	@Column(name = "name")
	private String name;
	
	@Column(name = "brand")
	private String brand;
	
	@Column(name = "size")
	private String size;
	
	@Column(name = "price")
	private Integer price;
	
	@Column(name = "principal_image")
	private String principalImage;
	
	@Column(name = "other_images")
	private String otherImages;

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

	public String getOtherImages() {
		return otherImages;
	}

	public void setOtherImages(String otherImages) {
		this.otherImages = otherImages;
	}

}
