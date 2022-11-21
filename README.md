# how to run the tests
How to run postman collection - steps:
a. Install node.js: https://nodejs.org/en/download/ 
b. Install newman: npm install -g newman
c. download locally the collection/json file from git hub
d. Run command from cmd: newman run hereputthepathwhereyousavedthejson\UnixTimestamp.postman_collection.json

Idea for improvment: When s is NULL, empty of malformated, the response code should not be 200, but 400 (bad request) and an error message
