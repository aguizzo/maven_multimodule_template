package com.itbulls.learnit.onlinestore.persistence.dto.converter;

import com.itbulls.learnit.onlinestore.persistence.dto.CategoryDto;

public class CategoryDtoToCategoryConverter {
	
	public CategoryDto convertCategoryNameToCategoryDtoWithOnlyName(String categoryName) {
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setCategoryName(categoryName);
		return categoryDto;
	}
}
