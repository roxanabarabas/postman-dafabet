# how to run the tests
## How to run postman collection - steps:
1. Install node.js: https://nodejs.org/en/download/ 
2. Install newman: npm install -g newman
3. download locally the collection/json file from git hub
4. Run command from cmd: newman run hereputthepathwhereyousavedthejson\UnixTimestamp.postman_collection.json

## Idea for improvment: 
1. When s is NULL, empty of malformated, the response code should not be 200, but 400 (bad request) and an error message
2. Different APIs for different input data type (one for a date string and one for s as a number)

## How to run Java project:
1. Download TestTime Java project
2. From cmd, go to the project path and run command: mvn test
