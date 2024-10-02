package com.example.FlightPriceComparator.Service;

import com.example.FlightPriceComparator.Models.Flight;
import com.example.FlightPriceComparator.UrlBuilder.CleartripUrl;
import com.example.FlightPriceComparator.UrlBuilder.PaytmUrl;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlightScraperService {

    private CleartripUrl cleartripUrl = new CleartripUrl();
    private PaytmUrl paytmUrl = new PaytmUrl();

    @Autowired
    public FlightScraperService(CleartripUrl cleartripUrl,
                                PaytmUrl paytmUrl) {
        this.cleartripUrl = cleartripUrl;
        this.paytmUrl = paytmUrl;
    }

    public List<Flight> Cleartrip(String travelData) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        String url = cleartripUrl.buildCleartripUrl(travelData);
        driver.get(url);

        List<Flight> cleartripFlights = new ArrayList<>();

        // Find all flight elements
        List<WebElement> flights = driver.findElements(By.cssSelector("div[data-testid='tupple']"));

        for (WebElement flight : flights) {

            // Get the flight operator
            String flightOperator = flight.findElement(By.cssSelector("p.fw-500.fs-2.c-neutral-900")).getText();

            // Get the flight number
            String flightNumber = flight.findElement(By.cssSelector("p.fs-1.c-neutral-400.pt-1")).getText();

            // Get the price
            String priceText = flight.findElement(By.cssSelector("p.m-0.fs-5.fw-700.c-neutral-900.ta-right.false")).getText();
            int price = Integer.parseInt(priceText.replaceAll("[^0-9]", "")); // Clean up price text

            // Create a flight object and add it to the list
            cleartripFlights.add(new Flight(flightOperator, flightNumber, price, null));
        }

        driver.quit();
        System.out.println("Number of flights scraped from Cleartrip: " + cleartripFlights.size()); // for debugging
        return cleartripFlights;
    }

    public List<Flight> Paytm(String travelDate) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        List<Flight> paytmFlights = new ArrayList<>();

        try {
            String url = paytmUrl.buildPaytmUrl(travelDate);
            driver.get(url);

            // Wait for the main flights container to load
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#app div._1-VZb")));

            // Now find the flights within the flights list
            List<WebElement> flights = driver.findElements(By.cssSelector("#flightsList div.M9kpV"));

            // Debugging: Check how many flight elements are found
            System.out.println("Number of flight elements found: " + flights.size());

            for (WebElement flight : flights) {
                // Extract flight details
                String flightOperator = flight.findElement(By.cssSelector("div._2OBb8 div._3ZFJJ span._2cP56")).getText();
                String flightNumber = flight.findElement(By.cssSelector("div._5Cbbf div._29g4q div._3x8TR div._2FGyN span._3gpc5")).getText();
                String priceText = flight.findElement(By.cssSelector("div._2PJH4 div._2MkSl")).getText();
                int price = Integer.parseInt(priceText.replaceAll("[^0-9]", "")); // Clean up price text

                paytmFlights.add(new Flight(flightOperator, flightNumber, null, price));
            }
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        } finally {
            driver.quit(); // Ensure the driver quits
        }

        System.out.println("Number of flights scraped from Paytm: " + paytmFlights.size()); // Debugging
        return paytmFlights;
    }
}


//    public List<Flight> Paytm(String travelDate) {
//
//        WebDriverManager.chromedriver().setup();
//        WebDriver driver = new ChromeDriver();
//
//        String url = paytmUrl.buildPaytmUrl(travelDate);
//        driver.get(url);
//
//        List<WebElement> flights = driver.findElements(By.className("flight-info"));
//        List<Flight> paytmFlights = new ArrayList<>();
//
//        for (WebElement flight : flights) {
//            String flightOperator = flight.findElements(By.className("operator-name")).toString();
//            String flightNumber = flight.findElements(By.className("flight-number")).toString();
//            String price = flight.findElements(By.className("price")).toString();
//
//            paytmFlights.add(new Flight(flightOperator, flightNumber, null, Integer.parseInt(price)));
//        }
//
//        driver.quit();
//        System.out.println("Number of flights scraped from Paytm: " + paytmFlights.size());
//        return paytmFlights;
//    }