package com.cortex.currency.converter.services;

import com.cortex.currency.converter.dto.CurrencyConverterDTO;
import com.cortex.currency.converter.dto.ValueConvertedDTO;
import com.cortex.currency.converter.enums.SourceResponseType;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component("currencyConverterFromBCB")
public class CurrencyConverterFromBCB extends AbstractCurrencyConverterFrom {
  
  public CurrencyConverterFromBCB(
      @Qualifier("bcbCurrencyConverterService") CurrencyConverterService currencyConverterService) {
    super(currencyConverterService);
  }
  
  @Override
  @CachePut(cacheNames = "valueConverted", key = "#currencyConverterDTO")
  Optional<ValueConvertedDTO> getFrom(CurrencyConverterDTO currencyConverterDTO) {
    var bcbValue = ((Mono<ValueConvertedDTO>) currencyConverterService
        .converter(currencyConverterDTO));
    var valueConvertedDTO = new ValueConvertedDTO();
    bcbValue.subscribe(i -> {
      valueConvertedDTO.setCurrencyConverterDTO(currencyConverterDTO);
      valueConvertedDTO.setSourceResponse(SourceResponseType.LIVE.toString());
    });
    
    log.info("Data obtained from BCB - LIVE");
    return Optional.of(valueConvertedDTO);
  }
}
