package com.wm.item.inventory.management.datasetsMgt;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Component;

/**
 * Learning notes : By making extended to Reactive Mongo Repository  - thus making it a non blocking adapter now
 */

public interface ItemDocumentReactiveRepository extends ReactiveMongoRepository<Item,String> {

}
