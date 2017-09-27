package com.ka.testservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ka.testservice.dao.IItemDao;
import com.ka.testservice.entity.Item;

@Service
public class ItemService implements IItemService {
  @Autowired
  private IItemDao itemDao;

  @Override
  public Item getItemById(int id) {
    Item obj = itemDao.getItemById(id);
    return obj;
  }

  @Override
  public List<Item> getAllItems() {
    return itemDao.getAllItems();
  }

  @Override
  public synchronized boolean addItem(Item item) {
    if (itemDao.itemExists(item.getTitle(), item.getCategory())) {
      return false;
    } else {
      itemDao.addItem(item);
      return true;
    }
  }

  @Override
  public void updateItem(Item item) {
    itemDao.updateItem(item);
  }

  @Override
  public void deleteItem(int id) {
    itemDao.deleteItem(id);
  }
}
