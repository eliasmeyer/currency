package com.cortex.currency.data.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {
  
  @Value("${application.api.bcb.urlbase}")
  private String URL_BASE;
  @Value("${application.api.bcb.timeout}")
  private Integer timeout;
  
  @Bean
  public WebClient webClient() {
    ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient());
    return WebClient.builder()
        .baseUrl(URL_BASE)
        .clientConnector(connector)
        .build();
  }
  
  private HttpClient httpClient() {
    HttpClient httpClient = HttpClient.create()
        .tcpConfiguration(tcpClient -> {
          tcpClient = tcpClient.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, timeout);
          tcpClient = tcpClient.doOnConnected(conn -> conn
              .addHandlerLast(new ReadTimeoutHandler(timeout, TimeUnit.MILLISECONDS)));
          return tcpClient;
        })
        .wiretap(true);
    return httpClient;
  }
}
