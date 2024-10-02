//package com.example.FlightPriceComparator.Service;
//
//import com.example.FlightPriceComparator.Models.Flight;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class FlightComparisonService {
//
//    private FlightScraperService flightScraperService;
//
//    @Autowired
//    public FlightComparisonService(FlightScraperService flightScraperService) {
//        this.flightScraperService = flightScraperService;
//    }
//
//    public List<Flight> compareFlights(String travelDate) {
//        List<Flight> cleartripFlights = flightScraperService.Cleartrip(travelDate);
//        List<Flight> paytmFlights = flightScraperService.Paytm(travelDate);
//
//        List<Flight> finalComparison = new ArrayList<>();
//
//        for (Flight cleartripFlight : cleartripFlights) {
//            for (Flight paytmFlight : paytmFlights) {
//                if (cleartripFlight.getFlightNumber().equals(paytmFlight.getFlightNumber())) {
//                    finalComparison.add(new Flight(
//                            cleartripFlight.getFlightOperator(),
//                            cleartripFlight.getFlightNumber(),
//                            cleartripFlight.getPriceOnCleartrip(),
//                            paytmFlight.getPriceOnPaytm()
//                    ));
//                }
//            }
//        }
//
//        return finalComparison;
//    }
//}

package com.example.FlightPriceComparator.Service;

import com.example.FlightPriceComparator.Models.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightComparisonService {

    private final FlightScraperService flightScraperService;

    @Autowired
    public FlightComparisonService(FlightScraperService flightScraperService) {
        this.flightScraperService = flightScraperService;
    }

    public List<Flight> compareFlights(String travelDate) {
        List<Flight> cleartripFlights = flightScraperService.Cleartrip(travelDate);
        List<Flight> paytmFlights = flightScraperService.Paytm(travelDate);

        List<Flight> finalComparison = new ArrayList<>();

        // Create a simple map to track flight operators from Paytm flights
        List<String> paytmOperators = new ArrayList<>();
        for (Flight paytmFlight : paytmFlights) {
            paytmOperators.add(paytmFlight.getFlightOperator());
        }

        // Compare by flight operator
        for (Flight cleartripFlight : cleartripFlights) {
            if (paytmOperators.contains(cleartripFlight.getFlightOperator())) {
                finalComparison.add(new Flight(
                        cleartripFlight.getFlightOperator(),
                        cleartripFlight.getFlightNumber(),
                        cleartripFlight.getPriceOnCleartrip(),
                        null // Assuming we don't have Paytm price for this operator
                ));
            }
        }

        return finalComparison;
    }
}
