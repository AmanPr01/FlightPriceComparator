# Flight Price Comparator

## Overview

The **Flight Price Comparator** is a tool designed to help users find the best flight prices across various airlines and travel websites. It scrapes flight data from different sources, including Paytm, to provide a comprehensive comparison of available options for a specified travel date.

## Features

- **Scraping Flight Data:** Collects flight details, including operators, flight numbers, and prices.
- **User-Friendly Interface:** Simple and intuitive interface for users to input travel details.
- **Real-Time Data:** Fetches live flight prices, ensuring users get the most up-to-date information.
- **Comparative Analysis:** Displays a list of flights from multiple airlines to help users make informed decisions.

## Technologies Used

- **Java:** The primary programming language for backend development.
- **Selenium:** For web scraping flight data from Paytm and other Cleartrip.
- **Maven:** Dependency management.

## Getting Started

### Prerequisites

Make sure you have the following installed on your machine:

- **Java JDK** (version 21)
- **Maven**
- **Git**

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/AmanPr01/FlightPriceComparator.git
   cd FlightPriceComparator
   ```
2. Install the required dependencies:
   ```bash
   mvn install
   ```
3. Run the application:
   ```bash
   mvn exec:java -Dexec.mainClass="com.yourpackage.MainClass"
   ```
## Usage

1. **Launch the application.**
2. **Input your desired travel date from Bangalore to Delhi.**
3. **Hit the API in chrome "http://localhost:8080/compare-flights?travelDate=yourpreferreddate"** to fetch and display flight options.
4. **Review the list of flights and their prices** to find the best deal.

   
