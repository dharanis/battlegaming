package com.thanksgivingex.battlegaming.Service;

import com.thanksgivingex.battlegaming.Entity.Item;
import com.thanksgivingex.battlegaming.Repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    private static Logger LOGGER = LoggerFactory.getLogger(ItemService.class);


    @Autowired
    public ItemService(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    public Item saveItem(Item item){
        if(item.getId() != null ) {
            if (itemRepository.existsById(item.getId())) {
                LOGGER.info("ID already exists... Updating the name");
                return itemRepository.save(item);
            }
        }
                LOGGER.info("Inserting the new item");
                return itemRepository.save(item);
    }

    public Iterable<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Iterable<Item> getItemsByName(String name) {
        if (name.length() <= 0)
            throw new EntityNotFoundException();
        return itemRepository.findItemsByName(name);
    }

    public void deleteItemById(Long id) {
        if(!itemRepository.existsById(id))
        {
            throw new DeleteItemByIdNotFound();
        }
        itemRepository.deleteById(id);
        LOGGER.info("Item with ID :"+id+" got deleted");
    }
}


@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Item by Id not found to delete")
class DeleteItemByIdNotFound extends RuntimeException {
}
