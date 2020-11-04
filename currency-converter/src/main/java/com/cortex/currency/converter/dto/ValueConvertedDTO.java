package com.cortex.currency.converter.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ValueConvertedDTO {
  
  private CurrencyConverterDTO currencyConverterDTO;
  private BigDecimal value;
  private String sourceResponse;
  
}
