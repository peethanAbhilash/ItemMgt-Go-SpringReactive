package com.wm.item.inventory.management.functionalweb.handlers;

import com.wm.item.inventory.management.datasetsMgt.Item;
import com.wm.item.inventory.management.datasetsMgt.ItemDocumentReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ItemMgtHandler {

  @Autowired
  ItemDocumentReactiveRepository itemDocumentReactiveRepository;

  public Mono<ServerResponse> getAllItems(ServerRequest serverRequest){
          return ServerResponse.ok()
              .contentType(MediaType.APPLICATION_JSON)
              .body(itemDocumentReactiveRepository.findAll(),Item.class);

  }


}
