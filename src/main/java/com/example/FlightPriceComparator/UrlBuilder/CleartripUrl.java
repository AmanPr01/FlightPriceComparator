package com.example.FlightPriceComparator.UrlBuilder;

import org.springframework.stereotype.Service;

@Service
public class CleartripUrl {

    public String buildCleartripUrl(String travelDate) {

        String[] dateParts = travelDate.split("-");
        String formattedDate = dateParts[2] + "/" + dateParts[1] + "/" + dateParts[0];   // converting yyyy-mm-dd to dd/mm/yyyy

        return "https://www.cleartrip.com/flights/results?" +
                "adults=1&childs=0&infants=0&class=Economy" +
                "&depart_date=" + formattedDate +
                "&from=BLR&to=DEL" +
                "&intl=n" +
                "&origin=BLR%20-%20Bangalore,%20IN" +
                "&destination=DEL%20-%20New%20Delhi,%20IN" +
                "&sft=&sd=1727848676719&rnd_one=O&isCfw=false";
    }
}
