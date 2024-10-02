package com.example.FlightPriceComparator.Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Flight {

    private String flightOperator;
    private String flightNumber;
    private Integer priceOnCleartrip;
    private Integer priceOnPaytm;
}
