package com.example.classajunit.controller;

import com.example.classajunit.Utils.APIResponse;
import com.example.classajunit.model.Item;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAll_success() throws JSONException {
        String response = this.restTemplate.getForObject("/all-items", String.class);

        JSONAssert.assertEquals("[{id:101},{id:102},{id:103},{id:104}]", response,false);
    }

    @Test
    public void getById_successObject(){
        Item item = this.restTemplate.getForObject("/all-items/101", Item.class);
        assertEquals("Item1", item.getName());
        assertEquals(1000, item.getValue());
    }

    @Test
    public void getById_success(){
        ResponseEntity<Item> item = this.restTemplate.getForEntity("/all-items/101", Item.class);
        assertTrue(item.getStatusCode().is2xxSuccessful());
        assertEquals("Item1", Objects.requireNonNull(item.getBody()).getName());
        assertEquals(1000,item.getBody().getValue());
    }


//    @Test
//    public void getById_404(){
//
//    }
//
    @Test
    public void saveItem_success(){
        Item item = new Item(105,"Item",400,2);
        ResponseEntity<Item> responseEntity = this.restTemplate
                .postForEntity("/add-item", item, Item.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(800, Objects.requireNonNull(responseEntity.getBody()).getValue());
    }
    @Test
    public void saveItem_BadRequest(){
        Item item = new Item(106,"Item2",400,2);
        ResponseEntity<APIResponse> responseEntity = this.restTemplate
                .postForEntity("/add-item", item, APIResponse.class);
        assertEquals(400, responseEntity.getStatusCodeValue());
        assertEquals("Item with the same name found", Objects.requireNonNull(responseEntity.getBody()).getMessage());
    }
    



}
