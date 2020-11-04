package com.cortex.currency.data.runner.external;

import java.util.stream.Stream;

public interface CurrencyService<T> {
  
  Stream<T> getAllCurrencies();
}
