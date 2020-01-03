package com.wm.item.inventory.management.gateways.v1;

import com.wm.item.inventory.management.datasetsMgt.Item;
import com.wm.item.inventory.management.datasetsMgt.ItemDocumentReactiveRepository;
import com.wm.item.inventory.management.datasetsMgt.constants.ItemMgtConstants;
import java.util.Arrays;
import java.util.List;
//import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * Create endpoints testing for the Item Management API.
 */

@WebFluxTest
@AutoConfigureWebTestClient
@RunWith(SpringRunner.class)
@DirtiesContext
@ActiveProfiles("test")
public class ItemMgtGatewayTest {

  @Autowired
  WebTestClient webTestClient;

  @Autowired
  ItemDocumentReactiveRepository itemDocumentReactiveRepository;

  @Before
  public void initDataSetup(){
    itemDocumentReactiveRepository.deleteAll()
        .thenMany(Flux.fromIterable(dummyDataItem()))
        .flatMap(itemDocumentReactiveRepository::save)
        .doOnNext(item-> System.out.println(item))
        .blockLast();
  }


  public List<Item> dummyDataItem() {
    return Arrays.asList(
        new Item(null, "Samsung TV", 2455.00),
        new Item(null, "LG TV ", 1455.00),
        new Item(null, "Sony TV", 1550.00),
        new Item("TCL", "TCL TV", 1655.00),
        new Item("VZ1", "Vizio TV", 655.00)
    );

  }

// @Test
  public  void getAllItemTest(){
    webTestClient.get().uri(ItemMgtConstants.itemGetAllEndPoints_v1)
        .exchange()   // the actual connection to endpoints happens here
        .expectStatus().isOk()
        .expectHeader().contentType(MediaType.APPLICATION_JSON)
        .expectBodyList(Item.class)
        .hasSize(5);
 }


}
