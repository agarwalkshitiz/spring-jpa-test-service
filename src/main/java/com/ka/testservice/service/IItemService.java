package com.ka.testservice.service;

import java.util.List;

import com.ka.testservice.entity.Item;

public interface IItemService {
  List<Item> getAllItems();

  Item getItemById(int id);

  boolean addItem(Item item);

  void updateItem(Item item);

  void deleteItem(int id);
}
