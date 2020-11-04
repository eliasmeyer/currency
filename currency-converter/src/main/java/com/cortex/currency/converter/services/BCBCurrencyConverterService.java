package com.cortex.currency.converter.services;

import com.cortex.currency.converter.dto.CurrencyConverterDTO;
import com.cortex.currency.converter.dto.ValueConvertedDTO;
import com.cortex.currency.converter.exception.ExternalFetchDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.UnsupportedMediaTypeException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component("bcbCurrencyConverterService")
class BCBCurrencyConverterService implements
    CurrencyConverterService<CurrencyConverterDTO, Mono<ValueConvertedDTO>> {
  
  @Autowired
  @Qualifier("webClientBCB")
  private WebClient webClient;
  
  @Override
  public Mono<ValueConvertedDTO> converter(CurrencyConverterDTO currencyConverterDTO) {
    return
        webClient
            .get()
            .uri("/converter/{money}/1/{codeCurrencySource}/{codeCurrencyTarget}/{date}",
                currencyConverterDTO.getConverterValue(),
                currencyConverterDTO.getCodeCurrencySource(),
                currencyConverterDTO.getCodeCurrencyTarget(),
                currencyConverterDTO.getQuoteDate())
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
            .bodyToMono(ValueConvertedDTO.class)
            //If there is any other type of return or if you cannot connect to the url, the return is empty
            //causes error on BodyExtractors - readWithMessageReaders
            .onErrorMap(UnsupportedMediaTypeException.class,
                throwable -> {
                  log.error("ERROR WHEN CONSUMING THE EXTERNAL API", throwable);
                  return new ExternalFetchDataException(
                      "ERROR WHEN CONSUMING THE EXTERNAL API",
                      throwable);
                });
  }
}
