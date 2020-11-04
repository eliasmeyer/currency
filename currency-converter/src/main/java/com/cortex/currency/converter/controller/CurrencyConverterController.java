package com.cortex.currency.converter.controller;

import com.cortex.currency.converter.services.CurrencyConverterServiceFacade;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping(path = "/currency", produces = MediaType.APPLICATION_JSON_VALUE)
public class CurrencyConverterController {
  
  @Autowired
  private CurrencyConverterServiceFacade currencyConverterServiceFacade;
  
  @PostMapping(path = "/converter", consumes = MediaType.APPLICATION_JSON_VALUE)
  public Mono<ResponseEntity<CurrencyConverterResponse>> converter(
      @Valid @RequestBody CurrencyConverterRequest request) {
    
    var valueDTO = currencyConverterServiceFacade.converter(request);
    var response = new CurrencyConverterResponse(valueDTO);
    return Mono.just(ResponseEntity.ok(response));
  }
}
