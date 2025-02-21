package com.qp.service.impl;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qp.entity.GroceryItemEntity;
import com.qp.exception.ResourceNotFoundException;
import com.qp.repository.GroceryItemRepository;
import com.qp.service.GroceryItemService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GroceryItemServiceImpl implements GroceryItemService {

	@Autowired
	private GroceryItemRepository groceryItemRepo;
	
	@Override
	public List<GroceryItemEntity> getAllItems() {
		return groceryItemRepo.findAll();
	}

	@Override
	public Optional<GroceryItemEntity> getItemById(Long id) {
		return Optional.ofNullable(groceryItemRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grocery item with ID " + id + " not found")));
    }

	@Override
	public GroceryItemEntity saveItem(GroceryItemEntity groceryItem) {
		if (groceryItem.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        if (groceryItem.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity must be at least 0");
        }
        log.info("Adding new grocery item: {}", groceryItem);
		return groceryItemRepo.save(groceryItem);
	}
	
	 public GroceryItemEntity updateInventory(Long id, int quantity) {
	        Optional<GroceryItemEntity> item = getItemById(id);
	        if (quantity < 0) {
	            throw new IllegalArgumentException("Quantity cannot be negative");
	        }
	        if(item.isPresent()) {
	        	item.get().setQuantity(quantity);
	        }
	        else {
	        	throw new ResourceNotFoundException("No such item exists");
	        }
	        return groceryItemRepo.save(item.get());
	    }

	@Override
	public void deleteItem(Long id) {
		groceryItemRepo.deleteById(id);
		
	}

	@Override
    public GroceryItemEntity updateGroceryItem(Long id, GroceryItemEntity groceryItem) {
        GroceryItemEntity existingItem = groceryItemRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        existingItem.setName(groceryItem.getName());
        existingItem.setPrice(groceryItem.getPrice());

        return groceryItemRepo.save(existingItem);
    }

}
