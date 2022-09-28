# Congestion Tax Calculator

Volvo Cars Congestion Tax Calculator assignment.
This repository contains a developer [assignment](ASSIGNMENT.md) used as a basis for candidate intervew and evaluati


## Step by step guide to know the implementation of the use case

## Tech stack
- Spring boot 
- java17
- Maven 
- Postman
- Intellij
- Lombok 


### Step1 
 Setting up spring boot Java application
 
### Step2 
  Create [application](application.yml) file which contains congestion tax rule properties 
  
### Step3 
   Create request and response [pojo's] of the congestion tax application
   
### Step4 
   Create plain pojo's for  tax rules and application properties
   
### Step5:
   Create service class all the requirement logic 

### Step7:
   Create controller with /tax-caliculate endpoint 
    



### How to run the App
```
java -jar .\target\volvo-congestion-tax-cal-0.0.1-SNAPSHOT.jar
```

```bash
mvn spring-boot:run
```

## How to test application 
REST Endpoint:

*API URL:*  http://localhost:8080/api/v1/tax-caliculate

*API method:*  **POST**

**Input Json:**

    {
        "vehicleType": "Car",
        "dates": [
            "2013-01-14 21:00:00",
            "2013-01-15 21:00:00",
            "2013-02-07 06:23:27",
            "2013-02-07 15:27:00",
 
        ]
    }
Output:

      {
            "vehicleType": "Tax calculated successfully, Vehicle :Car",
             "taxAmount": 21
       }



## What can be done on application if given more time?
- Swagger INTEGRATION
- Solution that coule be re usual for all use cases ex: handling different tax rules, 
- Add data persistence layer 
- Add support for global exception handling using [ @ControllerAdvice ] spring feature 
- Add test coverage both unit and spring integration tests
- Add security layer 
- Dockerise the application 
- Create CI pipeline using cloud service like GCP/AWS , terraform, Gitlab/jenkins
- Orchestrates using Kubernetes and deploy to cloud service
- Setup monitoring using prometheus and grafana

