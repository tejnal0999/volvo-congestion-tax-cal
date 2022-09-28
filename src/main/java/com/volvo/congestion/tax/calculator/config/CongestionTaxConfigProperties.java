package com.volvo.congestion.tax.calculator.config;

import com.volvo.congestion.tax.calculator.model.Holidays;
import com.volvo.congestion.tax.calculator.model.Timings;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "congestion-tax-rules")
@Data
public class CongestionTaxConfigProperties {

  private Integer singleCharge;
  private List<Timings> timings;
  private Holidays holidays;
  private List<String> city;
  private List<String> taxExemptVehicles;
}
