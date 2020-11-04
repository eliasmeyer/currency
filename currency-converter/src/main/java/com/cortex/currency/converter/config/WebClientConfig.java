package com.cortex.currency.converter.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {
  
  @Value("${application.api.connection.timeout}")
  private Integer API_CONN_TIMEOUT;
  @Value("${application.api.currency-data.urlbase}")
  private String CURRENCY_DATA_URL_BASE;
  @Value("${application.api.bcb.urlbase}")
  private String BCB_URL_BASE;
  
  @Bean(name = "webClientBCB")
  public WebClient webClient() {
    ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient());
    return WebClient.builder()
        .baseUrl(BCB_URL_BASE)
        .clientConnector(connector)
        .build();
  }
  
  @Bean(name = "webClientCurrencyData")
  public WebClient webClientCurrencyData() {
    ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient());
    return WebClient.builder()
        .baseUrl(CURRENCY_DATA_URL_BASE)
        .clientConnector(connector)
        .build();
  }
  
  
  private HttpClient httpClient() {
    HttpClient httpClient = HttpClient.create()
        .tcpConfiguration(tcpClient -> {
          tcpClient = tcpClient.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, API_CONN_TIMEOUT);
          tcpClient = tcpClient.doOnConnected(conn -> conn
              .addHandlerLast(new ReadTimeoutHandler(API_CONN_TIMEOUT, TimeUnit.MILLISECONDS)));
          return tcpClient;
        })
        .wiretap(true);
    return httpClient;
  }
}

