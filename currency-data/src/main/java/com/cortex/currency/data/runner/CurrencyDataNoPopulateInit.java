package com.cortex.currency.data.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnProperty(name = "application.database.populate", havingValue = "false")
public class CurrencyDataNoPopulateInit implements CurrencyDataPopulate {
  
  @Override
  public void init() {
    log.info("Initializing NOT populate database");
  }
}
