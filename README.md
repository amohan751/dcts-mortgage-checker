# Mortymeter: a mortgage and interest rate checker application

This is a Java-springboot based application using RESTful services to manage mortgage, interest rates and perform mortgage feasibility checks based on a set of input parameters.

## Endpoints

### GET /api/interest-rates
Retrieves a list of current interest rates available for interested customers. Returns maturity period, interest rate and the last updated timestamp for these values in database.

### POST /api/mortgage-check
Submit a set of parameters securely over post to calculate if a mortgage can be availed. Returns a boolean and applicable mortgage amount if "true".

## Getting Started

### Prerequisites
- JDK 17
- Maven

### Installing

1. Clone this repository:
   ```
   git clone https://github.com/amohan751/dcts-mortgage-checker.git

2. Navigate to the project directory:
   ```
   cd <your/path/to/mortymeter>
3. Issue the build command using maven
   ```
   mvn clean install
4. Run the application
   ```
   mvn spring-boot run

## Project Structure

### Mortgage Rate Object
A list of available current mortgage rates are configured and inserted into an H2 database on start up. The MortgageRate object contains the following fields:
- maturityPeriod (integer)
- interestRate (BigDecimal)
- lastUpdate (Timestamp)

### Mortgage Request Object
The posted data from mortgage-check API is taken to a mortgage calculator service for processing. The MortgageRequest object contains the following fields:
- income (BigDecimal)
- maturityPeriod (integer) considered to be in months
- loanValue (BigDecimal)
- homeValue (BigDecimal)

### Mortgage Response Object
The mortgage check return if the mortgage is feasible (true/false) and the montly costs (BigDecimal) of the mortgage. The MortgageResponse object contains the following fields:
- feasible (boolean)
- monthlyCost (BigDecimal)

### Mortgage Calculator
Is the service class which employs the logic as per the formula below to calculate the mortgage.
![screenshot of the mortgage formula](https://github.com/user-attachments/assets/6e680f8d-6b9d-4778-a1e7-c7558492c7c2)

Business rules that apply are:
- a mortgage should not exceed 4 times the income
- a mortgage should not exceed the home value
