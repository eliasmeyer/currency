package com.cortex.currency.converter.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class CurrencyConverterDTO {
  
  @JsonFormat(pattern = "dd/MM/yyyy")
  @EqualsAndHashCode.Include
  private LocalDate quoteDate;
  @EqualsAndHashCode.Include
  private String acronymSourceCurrency;
  @EqualsAndHashCode.Include
  private String acronymTargetCurrency;
  @JsonIgnore
  private Long codeCurrencySource;
  @JsonIgnore
  private Long codeCurrencyTarget;
  @EqualsAndHashCode.Include
  private BigDecimal converterValue;
  
  
}
