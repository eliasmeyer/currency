package com.cortex.currency.data.layers;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "currencies")
public class Currency {
  
  @Id
  private Long id;
  @Indexed
  private String acronym;
  private String formatName;
  private String name;
  private String symbol;
}

