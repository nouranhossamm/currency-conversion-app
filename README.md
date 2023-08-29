## Description

This project is a web application that allows users to convert currencies from one to another 
using real-time exchange rates. Users can also compare different rates from various sources.


## Architecture

The architecture of this project is based on the Model-View-Controller (MVC) pattern. 
The model layer consists of the classes that represent the domain entities and the business 
logic of the application. The view layer consists of the web pages that display the user 
interface and the data to the users. The controller layer consists of the classes that handle 
the user requests and communicate with the model and view layers.

The following diagram shows the high-level architecture of this project:

![Screenshot 2023-08-29 163415](https://github.com/Bassiouni/currency-conversion-app/assets/142849115/1e33aeef-b39e-4e4d-a5b5-95e8af93477a)



## Features

1.Currency Conversion: This application converting a given currency to another currency.

2.Currency Comparison: This application can also compare between two currencies.

3.Favorite List: This application allowing add currencies you use often to your favourites.



## API Contract

The API contract defines the format and structure of the requests and responses that 
are exchanged between the client and the server. The API contract follows the RESTful 
principles and uses JSON as the data format.

The following table summarizes the API endpoints and their parameters:

| *Endpoint*                       | *Method* | *Parameters*             |
|----------------------------------|----------|--------------------------|
| /getCurrencyInfo                 | GET      | currency_code            |
| /getCurrencyConversion           | GET      | current, target          |
| /getCurrencyConversionWithAmount | GET      | current, target, amount  |
| /getAvailableCurrencies          | GET      | currencies               |
| /getExchangeRate                 | GET      | current                  |
| /getCurrencyComparison           | GET      | basecode, targetcodes    |



## Structure File

The structure file describes the organization and hierarchy of the files and directories in this project. The structure file follows the tree command output format.

The following is the structure file of this project:


.
├── currency-conversion-app-master


     ├── .idea
     
     │   ├── .gitignore
     
     │   ├── compiler.xml
     
     │   ├── encoding.xml
     
     │   ├── jarRepositories.xml
     
     │   ├── misc.xml
     
     │   └── workspace.xml

     
     ├── .mvn
     
     │   ├── wrapper
     
     │       ├── maven-wrapper.jar
     
     │       ├── maven-wrapper.properties
     
     ├── src
     
     │   ├── main
     
     │   │   ├── java
     
     │   │   │   ├── com
     
     │   │   │   │   └── banquemisr.currencyconversionapp
     
     │   │   │   │       └── client
     
     │   │   │   │           └──ExchangeRateAPIClient
     
     │   │   │   │       └── config
     
     │   │   │   │           └── CachingConfig
     
     │   │   │   │       └── dto    
     │   │   │   │           ├── ComparisonDTO     
     │   │   │   │           ├── CurrencyComparisonRequestBodyPOJO     
     │   │   │   │           ├── CurrencyConversionDTO     
     │   │   │   │           ├── CurrencyDTO     
     │   │   │   │           └── UnitCurrencyConversionDTO     
     │   │   │   │       └── entities     
     │   │   │   │           └── Response     
     │   │   │   │       └── exception     
     │   │   │   │           ├── BadEntryException     
     │   │   │   │           ├── CustomExceptionHandler    
     │   │   │   │           └── NotFoundException     
     │   │   │   │       └── props     
     │   │   │   │           └── AppProps     
     │   │   │   │       └── service     
     │   │   │   │           └── ExchangeRateService     
     │   │   │   │       └── validation     
     │   │   │   │           ├── AmountValidation     
     │   │   │   │           ├── CurrencyExistsValidation    
     │   │   │   │           └── validate     
     │   │   │   │       └── web.controllers   
     │   │   │   │           └── ExchangeRateController
     │   │   │   │       └── CurrencyConversionAppApplication
     │   │   └── resources
     │   │       └── application.yml
     │   └── test
     │       └── java
     │           └── com
     │               └── banquemisr.currencyconversionapp
     │                   └── service
     │                       └── CurrencyConversionAppApplicationTests
     │
     ├── .env
     ├── .gitignore
     ├── docker-compose.yml
     ├── Dockefile
     ├── mvnw
     ├── mvnw.xml
     ├── pom.xml
     ├── README.md
