package com.thanksgivingex.battlegaming.Service;


import com.thanksgivingex.battlegaming.Controller.ItemController;
import com.thanksgivingex.battlegaming.Controller.RestEntity.RestItemEntity;
import com.thanksgivingex.battlegaming.Entity.Item;
import com.thanksgivingex.battlegaming.Repository.ItemRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ItemController.class, secure = false)
public class ItemServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    @MockBean
    private ItemRepository itemRepository;

    @Before
    public void setUp(){}

    @After
    public void tearDown(){}


    @Test
    public void createItemTest(){
        Item item = new Item();
        when(itemRepository.save(item)).thenReturn(item);
        itemService.saveItem(new Item());
        verify(itemRepository, Mockito.times(1)).save(item);
    }


}
