package com.example.FlightPriceComparator.UrlBuilder;

import org.springframework.stereotype.Service;

@Service
public class PaytmUrl {

    public String buildPaytmUrl(String travelDate) {
        return "https://tickets.paytm.com/flights/flightSearch/BLR-Bengaluru/DEL-Delhi/1/0/0/E/" + travelDate +
                "?utm_campaign=G_Web_Search_Flight_Paytm-Brand&utm_medium=cpc&utm_source=Google-ads";
    }
}
