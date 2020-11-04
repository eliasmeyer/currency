package com.cortex.currency.converter.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class CurrencyDataDTO {
  
  private Long id;
  private String acronym;
  private String formatName;
}
