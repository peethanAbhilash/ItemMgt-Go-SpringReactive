package com.wm.item.inventory.management.datasetsMgt.initialize;

import com.wm.item.inventory.management.datasetsMgt.Item;
import com.wm.item.inventory.management.datasetsMgt.ItemDocumentReactiveRepository;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@Profile("!test")
public class DummyItemDataInitializer implements CommandLineRunner {

  @Autowired
  ItemDocumentReactiveRepository itemDocumentReactiveRepository;


  @Override
  public void run(String... args) throws Exception {
    intialDataSetup();

  }

  public List<Item> dummyDataItem() {
    return Arrays.asList(
        new Item("SGTV", "Samsung TV", 2455.00),
        new Item("LGTV", "LG TV ", 1455.00),
        new Item( "SNTV", "Sony TV", 1550.00),
        new Item("TLTV", "TCL TV", 1655.00),
        new Item("VZTV", "Vizio TV", 655.00)
    );

  }


  private void intialDataSetup() {
    itemDocumentReactiveRepository.deleteAll()
        .thenMany(Flux.fromIterable(dummyDataItem()))
        .flatMap(itemDocumentReactiveRepository::save)
        .thenMany(itemDocumentReactiveRepository.findAll())
        .subscribe(item -> {
          System.out.println("Dummy Data Set up done on init --> " + item);
        });
  }


}
