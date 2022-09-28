package com.volvo.congestion.tax.calculator.service;

import com.volvo.congestion.tax.calculator.config.CongestionTaxConfigProperties;
import com.volvo.congestion.tax.calculator.model.Timings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CongestionTaxService {

  private static final int MAX_MINUTES = 60;


  private final CongestionTaxConfigProperties congestionTaxConfigProperties;
  public BigDecimal computeTax(List<LocalDateTime> vechicleEnrtyDateTime, String vehicleType) {

    BigDecimal totalTax = new BigDecimal(0);
    LocalDateTime[] dates = vechicleEnrtyDateTime.toArray(LocalDateTime[]::new);
    for (int i = 0; i < dates.length; i++) {
      BigDecimal taxFee;
      if (i == 0 || timeDiff(dates[i - 1], dates[i])) {
        taxFee = getTax(dates[i], vehicleType);
        totalTax = totalTax.add(taxFee);
      }
      }

    if (totalTax.intValue() > congestionTaxConfigProperties.getSingleCharge()) {
      totalTax = new BigDecimal(congestionTaxConfigProperties.getSingleCharge());
    }
    return totalTax;
  }

  private BigDecimal getTax(LocalDateTime vechicleEntryDate, String vehicle) {
      if (isTaxFreeDate(vechicleEntryDate)
          || isTaxFreeVehicle(vehicle)
          || noTaxMonth(vechicleEntryDate)) {
        return BigDecimal.valueOf(0);
      }

    Optional<Timings> timings =
        congestionTaxConfigProperties.getTimings().stream()
            .filter(timing -> timing.getTaxFromDate(vechicleEntryDate.toLocalTime()))
            .findFirst();

    if (timings.isPresent()) {
      return timings.get().getPrice();
    }
    return BigDecimal.valueOf(0);
  }

  // no tax in july month
  public boolean noTaxMonth(LocalDateTime localDateTime) {
    if (localDateTime.getMonthValue() == 6) {
      return true;
    }
    return false;
  }

  // validate exempt vehicles
  private boolean isTaxFreeVehicle(String vehicleType) {
    if (Objects.isNull(vehicleType)) {
      return false;
    }
    return congestionTaxConfigProperties.getTaxExemptVehicles().contains(vehicleType);
  }

  // validate is it tax free date
  private boolean isTaxFreeDate(LocalDateTime localDateTime) {
    int year = localDateTime.getYear();
    int month = localDateTime.getMonthValue();
    DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
    int dayOfMonth = localDateTime.getDayOfMonth();
    if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) return true;
    return congestionTaxConfigProperties.getHolidays().getYears().stream()
        .filter(years -> years.getYear() == year)
        .flatMap(years -> years.getMonths().stream())
        .anyMatch(
            months ->
                months.getMonth() == month
                    && (months.getDates().isEmpty() || months.getDates().contains(dayOfMonth)));
  }

  private boolean timeDiff(LocalDateTime startDate, LocalDateTime endDate) {
    long diff = ChronoUnit.MINUTES.between(startDate, endDate);
    return diff > MAX_MINUTES;
  }
}
