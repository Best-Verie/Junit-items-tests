package com.example.classajunit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.classajunit.model.Item;
import com.example.classajunit.service.ItemService;

@RestController
public class ItemController {

	@Autowired
	private ItemService itemService;



	@PostMapping("/add-item")
	public ResponseEntity<?> addItem(@RequestBody  Item item){
		return itemService.addItem(item);
	}

	@GetMapping("/all-items")
	public List<Item> getAll(){
		
	return itemService.getAll();
	}
	
	@GetMapping("/all-items/{id}")
	public Item getItemById(@PathVariable(name="id") int id) {
		return itemService.getById(id);
	}
}
