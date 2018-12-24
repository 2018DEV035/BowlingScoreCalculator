American Bowling Game Score Calculator
======================================

- The intent of this Kata is to implement the score calculation functionality of 10 pin American Bowling game.
- The Kata is based on the problem description mentioned in the link **http://codingdojo.org/kata/Bowling/**

Assumptions
-----------

The following assumptions are made as per problem description,

- We will not check for valid rolls.
- We will not check for correct number of rolls and frames.
- We will not provide scores for intermediate frames.
- All frame details will be given at once.

Steps to Run
------------
- Clone repo and import the project as Maven Project.
- The project can be cleaned with below command (cmd/terminal)
  `mvn clean`
- The test reports can be generated using below command. The mutation coverage reports can be found in */target/pit-reports/*
  `mvn test`
- The application can be installed with below command.
  `mvn install`
- The application can be booted with below command.
  `mvn spring-boot:run`
- Default port is **8080** and default logger level is **INFO**. Both these values can be configured in application.properties.
- Navigate to **localhost:8080/swagger-ui.html** for available APIs and documentation.
- Navigate to **ScoreController** and select **/calculateScore/** API.
- Click **Try it Out**
- Enter all input frame pins details in request. 
- Click **Execute** to calculate score.
- Sample curl request
  `curl -X POST "http://localhost:8080/score/calculateScore" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"frames\": [ { \"firstRoll\": 1, \"secondRoll\": 0 }, { \"firstRoll\": 1, \"secondRoll\": 0 }, { \"firstRoll\": 1, \"secondRoll\": 0 }, { \"firstRoll\": 1, \"secondRoll\": 0 }, { \"firstRoll\": 1, \"secondRoll\": 0 }, { \"firstRoll\": 1, \"secondRoll\": 0 }, { \"firstRoll\": 1, \"secondRoll\": 0 }, { \"firstRoll\": 1, \"secondRoll\": 0 }, { \"firstRoll\": 1, \"secondRoll\": 0 }, { \"firstRoll\": 1, \"secondRoll\": 0 } ]}"`