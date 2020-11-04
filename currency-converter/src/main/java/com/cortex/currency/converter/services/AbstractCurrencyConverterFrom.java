package com.cortex.currency.converter.services;

import com.cortex.currency.converter.dto.CurrencyConverterDTO;
import com.cortex.currency.converter.dto.ValueConvertedDTO;
import java.util.Optional;

abstract class AbstractCurrencyConverterFrom {
  
  protected final CurrencyConverterService currencyConverterService;
  
  public AbstractCurrencyConverterFrom(
      CurrencyConverterService currencyConverterService) {
    this.currencyConverterService = currencyConverterService;
  }
  
  abstract Optional<ValueConvertedDTO> getFrom(CurrencyConverterDTO currencyConverterDTO);
}
