package com.volvo.congestion.tax.calculator.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaxResponse {

  @JsonProperty private String vehicleType;
  @JsonProperty private BigDecimal taxAmount;
}
