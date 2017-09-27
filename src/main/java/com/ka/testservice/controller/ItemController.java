package com.ka.testservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.ka.testservice.entity.Item;
import com.ka.testservice.service.IItemService;

@Controller
@RequestMapping("user")
public class ItemController {
  @Autowired
  private IItemService testService;

  @GetMapping("item/{id}")
  public ResponseEntity<Item> getItemById(@PathVariable("id") Integer id) {
    Item item = testService.getItemById(id);
    return new ResponseEntity<Item>(item, HttpStatus.OK);
  }

  @GetMapping("items")
  public ResponseEntity<List<Item>> getAllItems() {
    List<Item> list = testService.getAllItems();
    return new ResponseEntity<List<Item>>(list, HttpStatus.OK);
  }

  @PostMapping("item")
  public ResponseEntity<Void> addItem(@RequestBody Item item, UriComponentsBuilder builder) {
    boolean flag = testService.addItem(item);
    if (flag == false) {
      return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }
    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(builder.path("/item/{id}").buildAndExpand(item.getId()).toUri());
    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
  }

  @PutMapping("item")
  public ResponseEntity<Item> updateItem(@RequestBody Item item) {
    testService.updateItem(item);
    return new ResponseEntity<Item>(item, HttpStatus.OK);
  }

  @DeleteMapping("item/{id}")
  public ResponseEntity<Void> deleteItem(@PathVariable("id") Integer id) {
    testService.deleteItem(id);
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }
}
