package com.thanksgivingex.battlegaming.Controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanksgivingex.battlegaming.Controller.RestEntity.RestItemEntity;
import com.thanksgivingex.battlegaming.Entity.Item;
import com.thanksgivingex.battlegaming.Service.ItemService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
//@WebMvcTest(value = ItemController.class, secure = false)
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;
    List<Item> items;
    @Before
    public void setUp(){
        items = Arrays.asList(new Item(2L, "rifle"),
                new Item(3L, "rifle"),
                new Item(4L, "knife"),
                new Item(5L, "knife")
        );
        //item = itemService.saveItem(new Item(1L,"sword"));
    }

    @After
    public void tearDown(){}


    //Create where object not in data store.

    @Test
    public void createItemTest() throws Exception {
        Item item = items.get(0);
        when(itemService.saveItem(item)).thenReturn(item);

        mockMvc.perform(MockMvcRequestBuilders
        .post("/api/create")
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(item))
        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        verify(itemService , times(1)).saveItem(isA(Item.class));
    }

    //Create where object is in data store.
    @Test
    public void updateItem() throws Exception {
        Item item = items.get(0);
        when(itemService.saveItem(item)).thenReturn(item);

        mockMvc.perform(MockMvcRequestBuilders
        .post("/api/create")
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(item))
        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(itemService , times(1)).saveItem(isA(Item.class));

    }

    //Get all items in the registry.
    @Test
    public void getAllItems() throws Exception {
        when(itemService.getAllItems()).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders
        .get("/api/get")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(itemService, Mockito.times(1)).getAllItems();
    }

    //Get all items of a specific type
    @Test
    public void getItemsByName() throws Exception {
        when(itemService.getItemsByName(anyString())).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/get/rifle")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(itemService, Mockito.times(1)).getItemsByName(anyString());
    }

    //Delete item that IS in the data store.
    @Test
    public void deleteItemById() throws Exception {
        doNothing().when(itemService).deleteItemById(anyLong());

        mockMvc.perform(MockMvcRequestBuilders
        .delete("/api/delete/{id}",anyLong()))
                .andExpect(status().isOk())
                .andReturn();

        verify(itemService,times(1)).deleteItemById(anyLong());
    }

}
