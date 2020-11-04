package com.cortex.currency.data.runner;

import com.cortex.currency.data.runner.external.BCBCurrencyDTO;
import com.cortex.currency.data.runner.external.CurrencyService;
import com.cortex.currency.data.layers.CurrencyRepository;
import com.cortex.currency.data.mapper.CurrencyMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnProperty(name = "application.database.populate", havingValue = "true")
public class CurrencyDataPopulateInit implements CurrencyDataPopulate {
  
  @Autowired
  private CurrencyService<BCBCurrencyDTO> currencyService;
  @Autowired
  private CurrencyMapper currencyMapper;
  @Autowired
  protected CurrencyRepository currencyRepository;
  
  @Override
  public void init() {
    log.info("Initialized POPULATE database");
    /*final var currencyStream = currencyMapper.from(externalFetchData.getAllCurrencies());
    currencyRepository.deleteAll()
        .thenMany(currencyRepository.saveAll(currencyStream.collect(Collectors.toList())))
        .subscribe();*/
    
    log.info("Finished POPULATE database");
  }
}
