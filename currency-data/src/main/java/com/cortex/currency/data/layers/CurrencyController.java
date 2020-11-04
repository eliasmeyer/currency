package com.cortex.currency.data.layers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping(value = "/currency", produces = MediaType.APPLICATION_JSON_VALUE)
public class CurrencyController {
  
  @Autowired
  private CurrencyRepository currencyRepository;
  
  @GetMapping("/acronyms/{acronym}")
  public Mono<ResponseEntity<Currency>> findByAcronym(@PathVariable("acronym") String acronym) {
    return currencyRepository.findByAcronym(acronym)
        .map(ResponseEntity::ok)
        .switchIfEmpty(Mono.defer(() -> {
          log.info("No Currency found with acronym [{}]", acronym);
          return Mono.just(ResponseEntity.notFound().build());
        }));
  }
}
