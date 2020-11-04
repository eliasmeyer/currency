package com.cortex.currency.converter.services;

import com.cortex.currency.converter.dto.CurrencyConverterDTO;
import com.cortex.currency.converter.dto.ValueConvertedDTO;

public interface CurrencyConverterService<P,R> {
  
  R converter(P parameter);
  
}
