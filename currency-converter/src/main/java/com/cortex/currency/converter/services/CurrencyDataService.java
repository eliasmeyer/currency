package com.cortex.currency.converter.services;

import com.cortex.currency.converter.dto.CurrencyDataDTO;
import reactor.core.publisher.Mono;

public interface CurrencyDataService {
  
  Mono<CurrencyDataDTO> findByAcronym(String acronym);
  
}
