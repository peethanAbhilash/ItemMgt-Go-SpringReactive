package com.wm.item.inventory.management.gatewayMgt.v1;

import static com.wm.item.inventory.management.datasetsMgt.constants.ItemMgtConstants.itemGetAllEndPoints_v1;

import com.wm.item.inventory.management.datasetsMgt.Item;
import com.wm.item.inventory.management.datasetsMgt.ItemDocumentReactiveRepository;
import com.wm.item.inventory.management.datasetsMgt.constants.ItemMgtConstants;
import com.wm.item.inventory.management.datasetsMgt.initialize.ItemCacheRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * Create endpoints testing for the Item Management API.
 */

@RestController
@Slf4j
public class ItemMgtGateway {

  @Autowired
  ItemCacheRepository itemCacheRepository;

  @Autowired
  ItemDocumentReactiveRepository itemDocumentReactiveRepository;

  @GetMapping(itemGetAllEndPoints_v1+"/{id}")
  public Flux<Item> getAllItem(@PathVariable String id){
    System.out.println("Item Cache Repository Collection Size intial "+itemCacheRepository.getItemCacheRepository().size());
    itemCacheRepository.updateItemRepository("Dummy"+id,"Dummy"+id);
    System.out.println("Item Cache Repository Collection Size after update "+itemCacheRepository.getItemCacheRepository().size());
    return itemDocumentReactiveRepository.findAll();
  }



}
