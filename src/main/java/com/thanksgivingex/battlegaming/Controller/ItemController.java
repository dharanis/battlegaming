package com.thanksgivingex.battlegaming.Controller;


import com.thanksgivingex.battlegaming.Controller.RestEntity.RestItemEntity;
import com.thanksgivingex.battlegaming.Entity.Item;
import com.thanksgivingex.battlegaming.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService){this.itemService = itemService;}

    @GetMapping("/ping")
    public String ping(){
        return "This is Item Controller";
    }

    @GetMapping("/get")
    public Iterable<Item> getAllItems(){
        return itemService.getAllItems();
    }

    @GetMapping("/get/{name}")
    public Iterable<Item> getItemsByName(@PathVariable String name){
        try
        {
            return itemService.getItemsByName(name);
        }catch (EntityNotFoundException e){
            throw new ItemByNameNotFound();
        }

    }

    @PostMapping("/create")
    @ResponseStatus(code = HttpStatus.OK)
    public Item createItem(@RequestBody Item item){
        return itemService.saveItem(item);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteItemById(@PathVariable Long id){
        itemService.deleteItemById(id);
    }

}

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Item by Name not found")
class ItemByNameNotFound extends RuntimeException {
}

