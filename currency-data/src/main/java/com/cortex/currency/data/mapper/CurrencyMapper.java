package com.cortex.currency.data.mapper;

import com.cortex.currency.data.runner.external.BCBCurrencyDTO;
import com.cortex.currency.data.layers.Currency;
import java.util.stream.Stream;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {
  
  @Mappings({
      @Mapping(target = "id", source = "codigo"),
      @Mapping(target = "name", source = "nome"),
      @Mapping(target = "formatName", source = "nomeFormatado"),
      @Mapping(target = "acronym", source = "sigla"),
      @Mapping(target = "symbol", source = "simbolo")
  })
  Currency from(BCBCurrencyDTO currencyDTO);
  
  Stream<Currency> from(Stream<BCBCurrencyDTO> currencyDTOS);
  
}
