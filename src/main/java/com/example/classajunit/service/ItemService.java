package com.example.classajunit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.classajunit.model.Item;
import com.example.classajunit.repository.ItemRepository;
import com.example.classajunit.Utils.APIResponse;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	public ResponseEntity<?> addItem(Item item){
		item.setValue(item.getQuantity()* item.getPrice());
		Optional<Item> itemNameFound = itemRepository.findByName(item.getName());
		System.out.println(itemNameFound);

		if(itemNameFound.isPresent()){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIResponse(false,"Item with the same name found"));		}
		this.itemRepository.save(item);
		return ResponseEntity.ok(item);
	}
	
	public List<Item> getAll() {	
		List<Item> items = itemRepository.findAll();
		for(Item item:items) {
			item.setValue(item.getPrice()*item.getQuantity());
		}
		return items;
	}

	public Item getById(int id) {
		Optional<Item> itemOption = itemRepository.findById(id);
		if(itemOption.isPresent()) {
			itemOption.get().setValue(itemOption.get().getQuantity()* itemOption.get().getPrice());
			return itemOption.get();
		}
		return null;
	}

}
