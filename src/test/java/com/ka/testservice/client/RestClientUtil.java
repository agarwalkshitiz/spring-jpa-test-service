package com.ka.testservice.client;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.ka.testservice.entity.Item;

public class RestClientUtil {
  public void getItemByIdDemo() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    RestTemplate restTemplate = new RestTemplate();
    String url = "http://localhost:8080/user/item/{id}";
    HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
    ResponseEntity<Item> responseEntity =
        restTemplate.exchange(url, HttpMethod.GET, requestEntity, Item.class, 1);
    Item item = responseEntity.getBody();
    System.out.println(
        "Id:" + item.getId() + ", Title:" + item.getTitle() + ", Category:" + item.getCategory());
  }

  public void getAllItemsDemo() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    RestTemplate restTemplate = new RestTemplate();
    String url = "http://localhost:8080/user/items";
    HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
    ResponseEntity<Item[]> responseEntity =
        restTemplate.exchange(url, HttpMethod.GET, requestEntity, Item[].class);
    Item[] items = responseEntity.getBody();
    for (Item item : items) {
      System.out.println("Id:" + item.getId() + ", Title:" + item.getTitle() + ", Category: "
          + item.getCategory());
    }
  }

  public void addItemDemo() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    RestTemplate restTemplate = new RestTemplate();
    String url = "http://localhost:8080/user/item";
    Item objItem = new Item();
    objItem.setTitle("Spring REST Security using Hibernate");
    objItem.setCategory("Spring");
    HttpEntity<Item> requestEntity = new HttpEntity<Item>(objItem, headers);
    URI uri = restTemplate.postForLocation(url, requestEntity);
    System.out.println(uri.getPath());
  }

  public void updateItemDemo() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    RestTemplate restTemplate = new RestTemplate();
    String url = "http://localhost:8080/user/item";
    Item objItem = new Item();
    objItem.setId(1);
    objItem.setTitle("Update:Java Concurrency");
    objItem.setCategory("Java");
    HttpEntity<Item> requestEntity = new HttpEntity<Item>(objItem, headers);
    restTemplate.put(url, requestEntity);
  }

  public void deleteItemDemo() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    RestTemplate restTemplate = new RestTemplate();
    String url = "http://localhost:8080/user/item/{id}";
    HttpEntity<Item> requestEntity = new HttpEntity<Item>(headers);
    restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, 4);
  }

  public static void main(String args[]) {
    RestClientUtil util = new RestClientUtil();
    // util.getItemByIdDemo();
    util.getAllItemsDemo();
    // util.addItemDemo();
    // util.updateItemDemo();
    // util.deleteItemDemo();
  }
}
