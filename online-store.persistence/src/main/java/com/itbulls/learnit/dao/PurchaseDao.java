package com.itbulls.learnit.dao;

import java.util.List;

import com.itbulls.learnit.dto.PurchaseDto;

public interface PurchaseDao {
	
	void savePurchase(PurchaseDto order);

	List<PurchaseDto> getPurchasesByUserId(int userId);
	
	List<PurchaseDto> getPurchases();
}
