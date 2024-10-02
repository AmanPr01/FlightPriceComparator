package com.example.FlightPriceComparator.Controller;

import com.example.FlightPriceComparator.Models.Flight;
import com.example.FlightPriceComparator.Service.CSVService;
import com.example.FlightPriceComparator.Service.FlightComparisonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class FlightComparatorController {

    private final FlightComparisonService flightComparisonService;
    private final CSVService csvService;

    public FlightComparatorController(FlightComparisonService flightComparisonService,
                                      CSVService csvService) {
        this.flightComparisonService = flightComparisonService;
        this.csvService = csvService;
    }

    @GetMapping("/compare-flights")
    public String compareFlights(@RequestParam String travelDate) throws IOException {
        List<Flight> comparedFlights = flightComparisonService.compareFlights(travelDate);
        csvService.generateCSV(comparedFlights);
        return "Flights comparison saved to CSV.";
    }
}


/*
http://localhost:8080/compare-flights?travelDate=2024-10-06
 */