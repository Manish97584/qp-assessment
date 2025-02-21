package com.qp.controller;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qp.entity.GroceryItemEntity;
import com.qp.service.GroceryItemService;

@RestController
@RequestMapping("/api/grocery-items")
public class GroceryItemController {
    private final GroceryItemService groceryItemService;

    public GroceryItemController(GroceryItemService groceryItemService) {
        this.groceryItemService = groceryItemService;
    }

    @GetMapping
    public ResponseEntity<List<GroceryItemEntity>> getAllItems() {
        return ResponseEntity.ok(groceryItemService.getAllItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroceryItemEntity> getItemById(@PathVariable Long id) {
        Optional<GroceryItemEntity> item = groceryItemService.getItemById(id);
        return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GroceryItemEntity> createItem(@RequestBody GroceryItemEntity groceryItem) {
        return ResponseEntity.ok(groceryItemService.saveItem(groceryItem));
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<GroceryItemEntity> updateGroceryItem(@PathVariable Long id, 
                                                               @RequestBody GroceryItemEntity groceryItem) {
        GroceryItemEntity updatedItem = groceryItemService.updateGroceryItem(id, groceryItem);
        return ResponseEntity.ok(updatedItem);
    }
    
    @PatchMapping("/{id}/update-quantity")
    public ResponseEntity<GroceryItemEntity> updateInventory(@PathVariable Long id, @RequestParam int quantity) {
        return ResponseEntity.ok(groceryItemService.updateInventory(id, quantity));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        groceryItemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
