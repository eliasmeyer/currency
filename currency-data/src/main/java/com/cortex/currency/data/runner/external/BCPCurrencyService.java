package com.cortex.currency.data.runner.external;

import com.cortex.currency.data.runner.external.exception.ExternalFetchDataException;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.UnsupportedMediaTypeException;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
public class BCPCurrencyService implements CurrencyService {
  
  @Autowired
  private WebClient webClient;
  
  @Override
  public Stream<BCBCurrencyDTO> getAllCurrencies() {
    return
        webClient
            .get()
            .uri("/moeda/data")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .onStatus(HttpStatus::is4xxClientError, clientResponse -> {
              log.error("ERROR WHEN CONSUMING THE EXTERNAL API - HTTP CODE 4XX CLIENT ERROR");
              throw new ExternalFetchDataException(
                  "ERROR when consuming the external API - HTTP CODE 4XX CLIENT ERROR");
            })
            .onStatus(HttpStatus::is5xxServerError, clientResponse -> {
              log.error("ERROR WHEN CONSUMING THE EXTERNAL API - 5XX SERVER ERROR");
              throw new ExternalFetchDataException(
                  "ERROR when consuming the external API - HTTP CODE 5XX SERVER ERROR");
            })
            .bodyToFlux(BCBCurrencyDTO.class)
            //If there is any other type of return or if you cannot connect to the url, the return is empty
            //causes error on BodyExtractors - readWithMessageReaders
            .onErrorMap(UnsupportedMediaTypeException.class,
                throwable -> {
                  log.error("ERROR WHEN CONSUMING THE EXTERNAL API", throwable);
                  return new ExternalFetchDataException(
                      "ERROR WHEN CONSUMING THE EXTERNAL API",
                      throwable);
                })
            .toStream();
  }
}
