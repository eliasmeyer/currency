package com.cortex.currency.data.mapper;

import com.cortex.currency.data.layers.Currency;
import com.cortex.currency.data.runner.external.BCBCurrencyDTO;
import java.util.stream.Stream;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-03T00:50:25-0300",
    comments = "version: 1.4.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.1.1.jar, environment: Java 11.0.9 (Amazon.com Inc.)"
)
@Component
public class CurrencyMapperImpl implements CurrencyMapper {

    @Override
    public Currency from(BCBCurrencyDTO currencyDTO) {
        if ( currencyDTO == null ) {
            return null;
        }

        Currency currency = new Currency();

        currency.setId( currencyDTO.getCodigo() );
        currency.setName( currencyDTO.getNome() );
        currency.setFormatName( currencyDTO.getNomeFormatado() );
        currency.setAcronym( currencyDTO.getSigla() );
        currency.setSymbol( currencyDTO.getSimbolo() );

        return currency;
    }

    @Override
    public Stream<Currency> from(Stream<BCBCurrencyDTO> currencyDTOS) {
        if ( currencyDTOS == null ) {
            return null;
        }

        return currencyDTOS.map( bCBCurrencyDTO -> from( bCBCurrencyDTO ) );
    }
}
