package com.itbulls.learnit.dao;

import com.itbulls.learnit.dto.CategoryDto;

public interface CategoryDao {
	
	CategoryDto getCategoryByCategoryId(int id);
	
}
