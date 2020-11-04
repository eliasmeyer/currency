package com.cortex.currency.converter.services;

import com.cortex.currency.converter.dto.CurrencyConverterDTO;
import com.cortex.currency.converter.dto.ValueConvertedDTO;
import com.cortex.currency.converter.enums.SourceResponseType;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Slf4j
@Component("currencyConverterFromCache")
public class CurrencyConverterFromCache extends AbstractCurrencyConverterFrom {
  
  public CurrencyConverterFromCache(
      @Qualifier("cacheCurrencyConverterService") CurrencyConverterService currencyConverterService) {
    super(currencyConverterService);
  }
  
  @Override
  Optional<ValueConvertedDTO> getFrom(CurrencyConverterDTO currencyConverterDTO) {
    var cacheValue = Optional.ofNullable((ValueConvertedDTO)
        currencyConverterService.converter(currencyConverterDTO));
    
    cacheValue.ifPresent(dto -> dto.setSourceResponse(SourceResponseType.CACHE.toString()));
    return cacheValue;
  }
}
