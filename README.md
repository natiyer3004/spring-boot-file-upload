# spring-boot-file-upload
Sample project to demonstrate file upload using Spring Boot and REST API.
To test the REST API, use any REST client (e.g., Postman) to invoke using the following:
url: http://localhost:8080/rest/api/upload
POST parameters, e.g.,: file (Test.txt), documentPath (/path/to/upload), keyWords (Test,Sample).
To run the REST application, use the maven goal: spring-boot:run . If you are running the file upload server 
from a desktop Windows environment, please create a folder C:/DocumentRepository in your local desktop to store 
the uploaded files or update the global.properties file with the preferred repository location.
