package com.itbulls.learnit.onlinestore.core.services.impl;

import java.util.List;

import com.itbulls.learnit.onlinestore.core.services.ProductManagementService;
import com.itbulls.learnit.onlinestore.persistence.dao.ProductDao;
import com.itbulls.learnit.onlinestore.persistence.dao.impl.MySqlJdbcProductDao;
import com.itbulls.learnit.onlinestore.persistence.dto.converter.ProductDtoToProductConverter;
import com.itbulls.learnit.onlinestore.persistence.enteties.store.Product;

public class MySqlProductManagementService implements ProductManagementService {
	
	private static MySqlProductManagementService instance;
	private ProductDao productDao;
	private ProductDtoToProductConverter converter;
	
	private MySqlProductManagementService () {
		productDao = new MySqlJdbcProductDao();
		converter = new ProductDtoToProductConverter();
	}
	
	public static MySqlProductManagementService getInstance() {
		if (instance == null)
			instance = new MySqlProductManagementService();
		return instance;
	}
	
	@Override
	public List<Product> getProducts() {
		var productsDtos = productDao.getProducts();
		return converter.convertProductDtosToProducts(productsDtos);
	}

	@Override
	public Product getProductById(int productIdToAddToCart) {
		var product = productDao.getProductById(productIdToAddToCart);
		return converter.convertProductDtoToProduct(product);
	}

}
