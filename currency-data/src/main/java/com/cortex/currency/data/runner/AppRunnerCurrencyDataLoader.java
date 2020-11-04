package com.cortex.currency.data.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunnerCurrencyDataLoader implements ApplicationRunner {
  
  @Autowired
  private CurrencyDataPopulate currencyDataPopulate;
  
  @Override
  public void run(ApplicationArguments args) {
    currencyDataPopulate.init();
  }
}
