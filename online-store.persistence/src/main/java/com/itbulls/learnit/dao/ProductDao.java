package com.itbulls.learnit.dao;

import java.util.List;

import com.itbulls.learnit.dto.ProductDto;

public interface ProductDao {
	
	List<ProductDto> getProducts();

	ProductDto getProductById(int productId);
}
