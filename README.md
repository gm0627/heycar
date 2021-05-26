Hey Car Coding Challenge
	

Problem discovered:
======================
•	In the CSV file, the data of the power is in power-in-ps, whereas, in the JSON format it is in kW. I implemented a solution by using a single POJO class.

•	Using the concept of Composite key, the requirement that different dealers may have listings with the same code, and the listings was solved.


Executed tests and results:
===========================
Automated test cases were built and verified using JUnit4.
Scenarios tested:
•	Test 01: POST "/dealers"

•	Test 02: GET "/dealers"

•	Test 03: GET "/dealer/{dealerID}"

•	Test 04: POST "/upload_csv/{dealerID}"

•	Test 05: POST "/vehicle_listing/{dealerID}"

•	Test 06: GET "/search?make=mercedes&model=a 180&year=2014&color=black"


Ideas for further improvement:
==============================
If I had more time I would have:

•	implemented a solution using microservices or serverless architecture using AWS Lambda.

•	implemented a complete end-to-end solution i.e., the front-end as well as the backend.

•	implemented CI/CD process using OCI Containers - Docker.

•	implemented more API validation and integration test cases.


Architecture and tools:
==========================
•	The requirement was simple and hence used Layered architecture to solve the problem statement.

•	The Microservices approach would have been appropriate if the given resources had complex business use-cases.

•	Tools used: Spring Boot, MySQL, JUnit4, Postman, and Swagger.
