package com.cortex.currency.data.layers;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CurrencyRepository extends ReactiveMongoRepository<Currency, Long> {
  
  @Cacheable(value = "currency", key = "#acronym")
  Mono<Currency> findByAcronym(String acronym);
  
}
