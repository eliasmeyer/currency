package com.cortex.currency.converter.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CurrencyConverterRequest {
  
  private LocalDate quoteDate;
  @NotEmpty(message = "sourceCurrencyAcronym is mandatory")
  private String sourceCurrencyAcronym;
  @NotEmpty(message = "targetCurrencyAcronym is mandatory")
  private String targetCurrencyAcronym;
  @Positive(message = "value must be positive")
  private BigDecimal value;
  
}
