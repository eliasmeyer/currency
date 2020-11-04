package com.cortex.currency.data.runner.external.exception;

public class ExternalFetchDataException extends RuntimeException {
  
  public ExternalFetchDataException(String message) {
    super(message);
  }
  
  public ExternalFetchDataException(String s, Throwable throwable) {
    super(s, throwable);
  }
}
