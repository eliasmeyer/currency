package com.cortex.currency.converter.exception;

public class ExternalFetchDataException extends RuntimeException {
  
  public ExternalFetchDataException(String message) {
    super(message);
  }
  
  public ExternalFetchDataException(String s, Throwable throwable) {
    super(s, throwable);
  }
}
