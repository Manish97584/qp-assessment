package com.qp.service;

import java.util.*;

import com.qp.entity.GroceryItemEntity;

public interface GroceryItemService {
	
	 List<GroceryItemEntity> getAllItems();
	 
	 Optional<GroceryItemEntity> getItemById(Long id);
	 
	 GroceryItemEntity saveItem(GroceryItemEntity groceryItem);
	 
	 void deleteItem(Long id);

	GroceryItemEntity updateGroceryItem(Long id, GroceryItemEntity groceryItem);

	GroceryItemEntity updateInventory(Long id, int quantity);
}
