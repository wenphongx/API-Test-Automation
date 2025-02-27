# API-Test-Automation
API Automation Challenge Submission This repository contains automated test scripts designed to verify REST API functionalities using REST Assured &amp; TestNG. The test suite includes GET, POST, PUT, and DELETE operations, ensuring robust validation and error handling.


API Endpoint	Test Scenario	Test Input	Expected Status Code	Expected Result
GET /users/{id}	Fetch details of an existing user	Valid user ID	200 OK	User details are returned correctly
GET /users/{id}	Fetch details of a non-existing user	Non-existing user ID	404 Not Found	Error message returned
GET /users/{id}	Fetch user with invalid ID	Invalid user ID format	400 Bad Request	Error message for invalid ID
GET /users/{id}	Fetch deleted user	Previously deleted user ID	404 Not Found	Error message returned
GET /users/{id}	Fetch user without token	No authentication token	401 Unauthorized	Error message for missing authentication
GET /users/{id}	Fetch user with invalid token	Invalid authentication token	401 Unauthorized	Error message for invalid authentication

POST /users	Create a new user	Valid user details	201 Created	User is created successfully
POST /users	Create a user with duplicate email	Email already exists	422 Unprocessable Entity	Error message for duplicate email
POST /users	Create a user without email	Missing email field	422 Unprocessable Entity	Error message for missing email
POST /users	Create a user with invalid status	Invalid status value	422 Unprocessable Entity	Error message for invalid status
POST /users	Create a user with invalid gender	Invalid gender value	422 Unprocessable Entity	Error message for invalid gender
POST /users	Create a user without token	No authentication token	401 Unauthorized	Error message for missing authentication
POST /users	Create a user with invalid token	Invalid authentication token	401 Unauthorized	Error message for invalid authentication

PUT /users/{id}	Update an existing user	Valid user ID and updates	200 OK	User is updated successfully
PUT /users/{id}	Update a non-existing user	Non-existing user ID	404 Not Found	Error message for non-existing user
PUT /users/{id}	Update a user without token	No authentication token	401 Unauthorized	Error message for missing authentication
PUT /users/{id}	Update a user with invalid token	Invalid authentication token	401 Unauthorized	Error message for invalid authentication
PUT /users/{id}	Update a user with invalid status	Invalid status value	422 Unprocessable Entity	Error message for invalid status
PUT /users/{id}	Update a user with invalid gender	Invalid gender value	422 Unprocessable Entity	Error message for invalid gender

DELETE /users/{id}	Delete an existing user	Valid user ID	204 No Content	User is deleted successfully
DELETE /users/{id}	Delete a non-existing user	Non-existing user ID	404 Not Found	Error message for non-existing user
DELETE /users/{id}	Delete a user without token	No authentication token	401 Unauthorized	Error message for missing authentication
DELETE /users/{id}	Delete a user with invalid token	Invalid authentication token	401 Unauthorized	Error message for invalid authentication
