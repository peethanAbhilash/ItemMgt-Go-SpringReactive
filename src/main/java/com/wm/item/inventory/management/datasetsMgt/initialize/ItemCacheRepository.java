package com.wm.item.inventory.management.datasetsMgt.initialize;

import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class ItemCacheRepository {

  private ConcurrentHashMap<String,String> itemCacheDummyRepository=new ConcurrentHashMap<>(5);

  public void updateItemRepository(String key,String val){
    if(itemCacheDummyRepository.isEmpty()) loadDummyDataToCache();
    itemCacheDummyRepository.put(key, val);
  }

  public ConcurrentHashMap<String,String> getItemCacheRepository(){
    return itemCacheDummyRepository;
  }

  private void loadDummyDataToCache(){
    itemCacheDummyRepository.put("Dummy1","Dummy1");
    itemCacheDummyRepository.put("Dummy2","Dummy");
    itemCacheDummyRepository.put("Dummy3","Dummy3");
    itemCacheDummyRepository.put("Dummy4","Dummy4");
  }

}
