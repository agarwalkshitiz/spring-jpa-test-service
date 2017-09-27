package com.ka.testservice.dao;

import java.util.List;

import com.ka.testservice.entity.Item;

public interface IItemDao {
  List<Item> getAllItems();

  Item getItemById(int id);

  void addItem(Item item);

  void updateItem(Item item);

  void deleteItem(int id);

  boolean itemExists(String title, String category);
}
