package com.itbulls.learnit.onlinestore.core.services.impl;

import java.util.List;

import com.itbulls.learnit.onlinestore.core.services.PurchaseManagementService;
import com.itbulls.learnit.onlinestore.persistence.dao.PurchaseDao;
import com.itbulls.learnit.onlinestore.persistence.dao.impl.MySqlJdbcPurchaseDao;
import com.itbulls.learnit.onlinestore.persistence.dto.PurchaseDto;
import com.itbulls.learnit.onlinestore.persistence.dto.converter.PurchaseDtoToPurchaseConverter;
import com.itbulls.learnit.onlinestore.persistence.enteties.store.Purchase;

public class MySqlPurchaseManagementService implements PurchaseManagementService {
	
	private static MySqlPurchaseManagementService instance;
	
	private PurchaseDao purchaseDao;
	private PurchaseDtoToPurchaseConverter converter;
	
	private MySqlPurchaseManagementService() {
		purchaseDao = new MySqlJdbcPurchaseDao();
		converter = new PurchaseDtoToPurchaseConverter();
	}
	
	public static MySqlPurchaseManagementService getInstance() {
		if (instance == null)
			instance = new MySqlPurchaseManagementService();
		return instance;
	}
	
	@Override
	public void addPurchase(Purchase purchase) {
		purchaseDao.savePurchase(converter.convertPurchaseToPurchaseDto(purchase));
	}

	@Override
	public List<Purchase> getPurchasesByUserId(int userId) {
		List<PurchaseDto> purchases = purchaseDao.getPurchasesByUserId(userId);
		return converter.convertPurchaseDtosToPurchases(purchases);
	}

	@Override
	public List<Purchase> getPurchases() {
		List<PurchaseDto> purchases = purchaseDao.getPurchases();
		return converter.convertPurchaseDtosToPurchases(purchases);
	}

}
