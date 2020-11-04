package com.cortex.currency.data.runner.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BCBCurrencyDTO {
  
  private Long codigo;
  private String nome;
  private String nomeFormatado;
  private String sigla;
  private String simbolo;
  
}
