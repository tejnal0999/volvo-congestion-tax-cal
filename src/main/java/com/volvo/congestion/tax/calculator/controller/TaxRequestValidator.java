package com.volvo.congestion.tax.calculator.controller;

import com.volvo.congestion.tax.calculator.http.Cities;
import com.volvo.congestion.tax.calculator.http.TaxRequest;
import com.volvo.congestion.tax.calculator.http.VehicleType;

import org.springframework.stereotype.Component;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.util.Arrays;
import java.util.Optional;

@Component
public class TaxRequestValidator {

  public boolean validateRequest(TaxRequest taxRequest) {
    Optional<VehicleType> vehicleType =
        Arrays.stream(VehicleType.values())
            .filter(vehicleTypeEnum -> taxRequest.getVehicleType().equals(vehicleTypeEnum.name()))
            .findAny();

    Optional<Cities> city =
        Arrays.stream(Cities.values()).filter(c -> taxRequest.getCity().equals(c.name())).findAny();

    if (!vehicleType.isPresent() || !city.isPresent()) {
      throw Problem.valueOf(
          Status.BAD_REQUEST,
          String.format("Vehicle type: %s  (or) City: %s not supported", taxRequest.getVehicleType(), taxRequest.getCity()));
    }
    return true;
  }
}
