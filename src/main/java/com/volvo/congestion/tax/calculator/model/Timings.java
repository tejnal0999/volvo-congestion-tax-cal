package com.volvo.congestion.tax.calculator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class Timings {

    private BigDecimal price;
    private String startTime;
    private String endTime;

    public boolean getTaxFromDate(LocalTime localTime)  {
        if (localTime == null) {
            return false;
        }
        LocalTime localStartTime = LocalTime.parse(this.startTime);
        LocalTime localEndTime = LocalTime.parse(this.endTime);
        return localTime.isAfter(localStartTime) && localTime.isBefore(localEndTime);


    }


}
