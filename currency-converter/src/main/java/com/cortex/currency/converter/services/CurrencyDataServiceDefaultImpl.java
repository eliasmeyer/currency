package com.cortex.currency.converter.services;

import com.cortex.currency.converter.dto.CurrencyDataDTO;
import com.cortex.currency.converter.exception.CurrencyDataServiceFetchDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.UnsupportedMediaTypeException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
class CurrencyDataServiceDefaultImpl implements CurrencyDataService {
  
  @Autowired
  @Qualifier("webClientCurrencyData")
  private WebClient webClient;
  
  @Override
  public Mono<CurrencyDataDTO> findByAcronym(String acronym) {
    return
        webClient
            .get()
            .uri("/acronyms/{acronym}", acronym)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .onStatus(HttpStatus::is4xxClientError, clientResponse -> {
              log.error("ERROR WHEN CONSUMING THE EXTERNAL API - HTTP CODE 4XX CLIENT ERROR");
              throw new CurrencyDataServiceFetchDataException(
                  "ERROR when consuming the API Currency Data - HTTP CODE 4XX CLIENT ERROR");
            })
            .onStatus(HttpStatus::is5xxServerError, clientResponse -> {
              log.error("ERROR WHEN CONSUMING THE EXTERNAL API - 5XX SERVER ERROR");
              throw new CurrencyDataServiceFetchDataException(
                  "ERROR when consuming the API Currency Data - HTTP CODE 5XX SERVER ERROR");
            })
            .bodyToMono(CurrencyDataDTO.class)
            //If there is any other type of return or if you cannot connect to the url, the return is empty
            //causes error on BodyExtractors - readWithMessageReaders
            .onErrorMap(UnsupportedMediaTypeException.class,
                throwable -> {
                  log.error("ERROR WHEN CONSUMING THE API Currency Data", throwable);
                  return new CurrencyDataServiceFetchDataException(
                      "ERROR WHEN CONSUMING THE EXTERNAL API Currency Data",
                      throwable);
                });
  }
}
