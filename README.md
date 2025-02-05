# Mortymeter: a mortgage and interest rate checker application

This is a Java-springboot based application using RESTful services to manage mortgage, interest rates and perform mortgage feasibility checks based on a set of input parameters.

## Endpoints

### GET /api/interest-rates
Retrieves a list of current interest rates available for interested customers. Returns maturity period, interest rate and the last updated timestamp for these values in database.

### POST /api/mortgage-check
Submit a set of parameters securely over post to calculate if a mortgage can be availed. Returns a boolean and applicable mortgage amount if "true".

## Getting Started

### Prerequisites
- JDK 17 or higher
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
