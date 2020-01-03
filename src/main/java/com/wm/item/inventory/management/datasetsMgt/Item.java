package com.wm.item.inventory.management.datasetsMgt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Learning notes :  @Document is same as @Entity for RDBMS table
 */
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
  @Id
  private String id;
  private String description;
  private double price;
}
