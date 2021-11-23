package com.example.classajunit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.classajunit.model.Item;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>{

    Optional<Item> findByName(String name);
}
