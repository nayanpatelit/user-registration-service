# User Registration Service
This service is exposing an end point for user registration.

# List of end points:

curl -X 'POST' \
  'http://localhost:8080/user/registration' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "userName": "test",
  "password": "Test123889$",
  "ipAddress": "8.8.8.8"
}'

# Steps to run the service on local:
 1. Checkout the code from
  https://github.com/nayanpatelit/user-registration-service/tree/master

 2. Go to the project root folder
 3. Maven build ( Make sure Java 8 or 11 & Maven is installed)
    mvn clean install
 4. Run the Spring boot application
    java -jar target/user-registration-0.0.1-SNAPSHOT.jar

 5. Access swagger URL
   http://localhost:8080/swagger-ui/index.html

# Sample Request Payload:
{
  "userName": "test",
  "password": "Test123889$",
  "ipAddress": "8.8.8.8"
}
