This is a MAVEN built rewards calculator service 
Technologies Used : 

Java 11
Spring Boot

This project uses H2 in-memory Database 
Tables : 
1.Customer
2.Purchases
3.Points

Use data.sql to dynamically insert data into our 3 tables and calculate reward points

Steps to execute : 

Clone service from MASTER branch from repository https://github.com/SwapnaVadaram/RewardsCalculator.git using command
git clone https://github.com/SwapnaVadaram/RewardsCalculator.git -b master
Go to project folder and open command prompt
Build project using command "mvn clean install" to download dependencies
Run the service using the command "java -jar rewardscalculator-0.0.1-SNAPSHOT.jar"
Port given in application.properties is 65003 for ex : http://localhost:65003/pointscalculator/customer/1










