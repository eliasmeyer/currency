package com.cortex.currency.converter.controller;

import com.cortex.currency.converter.dto.ValueConvertedDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CurrencyConverterResponse {
  
  private final ValueConvertedDTO valueConverted;
}
