package com.cortex.currency.converter.exception;

public class CurrencyDataServiceFetchDataException extends RuntimeException {
  
  public CurrencyDataServiceFetchDataException(String message) {
    super(message);
  }
  
  public CurrencyDataServiceFetchDataException(String s, Throwable throwable) {
    super(s, throwable);
  }
}
