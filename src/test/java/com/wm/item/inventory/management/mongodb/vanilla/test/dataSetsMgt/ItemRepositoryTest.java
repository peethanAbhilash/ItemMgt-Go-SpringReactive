package com.wm.item.inventory.management.mongodb.vanilla.test.dataSetsMgt;
import com.wm.item.inventory.management.datasetsMgt.Item;
import com.wm.item.inventory.management.datasetsMgt.ItemDocumentReactiveRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * Learning Notes :
 * Using this help to load only required classes required for MongoDb test cases and not the whole app context.
 */
@DataMongoTest
@RunWith(SpringRunner.class)
@DirtiesContext
public class ItemRepositoryTest {

  private List<Item> itemList = Arrays.asList(
      new Item(null,"Mac Pro",2455.00),
      new Item(null,"Lenovo ",1455.00),
      new Item(null,"Microsoft Yoga",1550.00),
      new Item(null,"Microsoft Grato",1655.00),
      new Item("IP1","Mac Ipad",2655.00)
      );


  @Before
  public void initSetup(){
    itemDocumentReactiveRepository.deleteAll()
        .thenMany(Flux.fromIterable(itemList))
        .flatMap(itemDocumentReactiveRepository::save)
        .doOnNext(item -> {
          System.out.println("Saved item :"+item);
        }
        ).blockLast(); // Learning notes :  This will wait until all items got loaded and saved -
                                          //thus test cases will have all data ready.(to bne used on for test cases
  }

  @Autowired
  ItemDocumentReactiveRepository itemDocumentReactiveRepository;

  @Test
  public void getAllItems(){
    StepVerifier.create(itemDocumentReactiveRepository
        .findAll())
        .expectSubscription()
        .expectNextCount(5)
        .verifyComplete();
  }

  @Test
  public void getItemsById(){
    StepVerifier.create(itemDocumentReactiveRepository
        .findById("IP1"))
        .expectSubscription()
        .expectNextMatches(item->item.getDescription().equals("Mac Ipad"))
        .verifyComplete();
  }



}
