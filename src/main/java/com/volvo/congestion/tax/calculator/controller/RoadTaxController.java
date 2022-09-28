package com.volvo.congestion.tax.calculator.controller;

import com.volvo.congestion.tax.calculator.http.TaxRequest;
import com.volvo.congestion.tax.calculator.http.TaxResponse;
import com.volvo.congestion.tax.calculator.service.CongestionTaxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RoadTaxController {
  private final CongestionTaxService congestionTaxService;
  private final TaxRequestValidator taxRequestValidator;

  @PostMapping("/tax-caliculate")
  public ResponseEntity<TaxResponse> congestionTax(@Valid @RequestBody TaxRequest taxRequest) {
    TaxResponse taxResponse;
    if (taxRequestValidator.validateRequest(taxRequest)) {
      try {
        var tax =
            congestionTaxService.computeTax(
                taxRequest.getDates(), taxRequest.getVehicleType());
        taxResponse =
            new TaxResponse(
                "Tax calculated successfully, Vehicle :" + taxRequest.getVehicleType(), tax);
        return new ResponseEntity<>(taxResponse, HttpStatus.OK);
      } catch (Exception e) {
        taxResponse = new TaxResponse(e.getLocalizedMessage(), new BigDecimal(0));
        return new ResponseEntity<>(taxResponse, HttpStatus.BAD_REQUEST);
      }
    }
    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
  }
}
