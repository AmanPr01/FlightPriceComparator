package com.example.FlightPriceComparator.Service;

import com.example.FlightPriceComparator.Models.Flight;
import com.opencsv.CSVWriter;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Service
public class CSVService {

    public void generateCSV(List<Flight> flightData) throws IOException {
        String[] header = { "Flight Operator", "Flight Number", "Price on Cleartrip", "Price On Paytm"};

        try (CSVWriter writer = new CSVWriter(new FileWriter("flight_comparison.csv"))) {
            writer.writeNext(header);

            for (Flight flight : flightData) {
                String[] data = {
                        flight.getFlightOperator(),
                        flight.getFlightNumber(),
                        flight.getPriceOnCleartrip() != null ? flight.getPriceOnCleartrip().toString() : "No Data Found",
                        flight.getPriceOnPaytm() != null ? flight.getPriceOnPaytm().toString() : "No Data Found"
                };
                System.out.println("Writing flight data: " + Arrays.toString(data));
                writer.writeNext(data);
            }
        }
    }
}
