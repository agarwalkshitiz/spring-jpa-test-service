package com.ka.testservice.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ka.testservice.entity.Item;

@Transactional
@Repository
public class ItemDao implements IItemDao {
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public Item getItemById(int id) {
    return entityManager.find(Item.class, id);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Item> getAllItems() {
    String hql = "FROM Item as item ORDER BY item.id";
    return (List<Item>) entityManager.createQuery(hql).getResultList();
  }

  @Override
  public void addItem(Item item) {
    entityManager.persist(item);
  }

  @Override
  public void updateItem(Item item) {
    Item temp = getItemById(item.getId());
    temp.setTitle(item.getTitle());
    temp.setCategory(item.getCategory());
    entityManager.flush();
  }

  @Override
  public void deleteItem(int id) {
    entityManager.remove(getItemById(id));
  }

  @Override
  public boolean itemExists(String title, String category) {
    String hql = "FROM Item as item WHERE item.title = ? and item.category = ?";
    int count = entityManager.createQuery(hql).setParameter(1, title).setParameter(2, category)
        .getResultList().size();
    return count > 0 ? true : false;
  }
}
