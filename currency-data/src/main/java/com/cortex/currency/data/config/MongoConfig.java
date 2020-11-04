package com.cortex.currency.data.config;

import com.cortex.currency.data.layers.CurrencyRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackageClasses = CurrencyRepository.class)
public class MongoConfig extends AbstractReactiveMongoConfiguration {
  
  private final static String DATABASE = "cortex";
  
  @Value("${spring.data.mongodb.uri}")
  private String mongoUri;
  
  @Bean
  public MongoClient reactiveMongoClient() {
    return MongoClients.create(mongoUri);
  }
  
  
  @Override
  protected String getDatabaseName() {
    return DATABASE;
  }
}
