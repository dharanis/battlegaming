package com.thanksgivingex.battlegaming.Repository;


import com.thanksgivingex.battlegaming.Entity.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository <Item , Long> {

 Iterable<Item> findItemsByName(String ItemName);
}
