package com.cortex.currency.converter.services;

import com.cortex.currency.converter.dto.CurrencyConverterDTO;
import com.cortex.currency.converter.dto.ValueConvertedDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component("cacheCurrencyConverterService")
public class CacheCurrencyConverterService implements
    CurrencyConverterService<CurrencyConverterDTO, ValueConvertedDTO> {
  
  @Override
  @Cacheable(cacheNames = "valueConverted", key = "#currencyConverterDTO")
  public ValueConvertedDTO converter(CurrencyConverterDTO currencyConverterDTO) {
    return null;
  }
}
