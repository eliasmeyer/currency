package com.cortex.currency.converter.services;

import com.cortex.currency.converter.controller.CurrencyConverterRequest;
import com.cortex.currency.converter.dto.CurrencyConverterDTO;
import com.cortex.currency.converter.dto.CurrencyDataDTO;
import com.cortex.currency.converter.dto.ValueConvertedDTO;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CurrencyConverterServiceFacade {
  
  @Autowired
  private CurrencyDataService currencyDataService;
  @Autowired
  @Qualifier("currencyConverterFromBCB")
  private AbstractCurrencyConverterFrom currencyConverterFromBCB;
  @Autowired
  @Qualifier("currencyConverterFromCache")
  private AbstractCurrencyConverterFrom currencyConverterFromCache;
  
  public ValueConvertedDTO converter(CurrencyConverterRequest request) {
    var currencySource = currencyDataService
        .findByAcronym(request.getSourceCurrencyAcronym()).map(CurrencyDataDTO::getId);
    var currencyTarget = currencyDataService
        .findByAcronym(request.getTargetCurrencyAcronym()).map(CurrencyDataDTO::getId);
    
    var currencyConverterDTO = CurrencyConverterDTO.builder()
        .quoteDate(Optional.ofNullable(request.getQuoteDate()).orElse(LocalDate.now()))
        .converterValue(request.getValue())
        .acronymSourceCurrency(request.getSourceCurrencyAcronym())
        .acronymTargetCurrency(request.getTargetCurrencyAcronym())
        .build();
    
    currencySource.subscribe(id -> currencyConverterDTO.setCodeCurrencySource(id));
    currencyTarget.subscribe(id -> currencyConverterDTO.setCodeCurrencyTarget(id));
    return getFrom(currencyConverterDTO);
  }
  
  
  private ValueConvertedDTO getFrom(CurrencyConverterDTO currencyConverterDTO) {
    
    var convertedValue = currencyConverterFromCache.getFrom(currencyConverterDTO);
    
    if (!convertedValue.isPresent()) {
      convertedValue = currencyConverterFromBCB.getFrom(currencyConverterDTO);
    }
    
    return convertedValue.get();
  }
}
