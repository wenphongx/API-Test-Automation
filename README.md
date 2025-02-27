# API-Test-Automation
API Automation Challenge Submission This repository contains automated test scripts designed to verify REST API functionalities using REST Assured &amp; TestNG. The test suite includes GET, POST, PUT, and DELETE operations, ensuring robust validation and error handling.

# Project Setup
1️⃣ Prerequisites
Before you begin, ensure your system has:

🔹 Java JDK 8+ (JDK 11 or higher recommended) <br>
🔹 Maven 3+ (for dependency management) <br>
🔹 Git (for cloning the repository) <br>
🔹 IDE (such as IntelliJ IDEA, Eclipse, or VS Code) <br>

2️⃣ Clone the Repository
Run the following command in Terminal / Command Prompt to download this project: <br>
git clone https://github.com/wenphongx/API-Test-Automation.git <br>
cd API-Test-Automation <br>

3️⃣ Install Dependencies <br>
Since this project uses Maven, run the following command to download all required dependencies: <br>
mvn clean install <br>

4️⃣ 🔑 Setting the API Token <br>
This project requires a Bearer Token for authentication. <br>
Set Token via Command Line <br>
Run the following command before running tests: <br>
export API_TOKEN="your_api_token_here" ---> # For macOS/Linux <br>
set API_TOKEN="your_api_token_here"  --->    # For Windows (CMD) <br>
$env:API_TOKEN="your_api_token_here"  --->   # For Windows (PowerShell) <br>
🔹 Replace "your_api_token_here" with your actual API token. <br>


5️⃣ Run Tests <br>
📌 Run test cases by selecting the desired group <br>
mvn test <br>

Running Tests Manually in the IDE <br>
If you prefer to run tests manually instead of using the command line, you can do so directly from your IDE (IntelliJ IDEA, Eclipse, or VS Code). <br>

🔹 Option 1: Run Individual Test Cases <br>
Open your IDE and navigate to the src/test/java folder. <br>
Locate the test class you want to run (e.g., Post_API_CreateUser.java or SmockTest.java). <br>
Right-click on the test method (e.g., testCreateUser) or the class name. <br>
Select "Run 'testCreateUser()'" or "Run 'SmockTest'". <br>

# Project Structure <br>
📦 API-Test-Automation <br>
 ┣ 📂 src <br> 
 ┃ ┣ 📂 main <br> 
 ┃ ┃ ┗ 📂 java <br>
 ┃ ┃ ┃ ┣ 📜 BaseTest.java      ---->         # Base class for API setup <br>
 ┃ ┃ ┃ ┣ 📜 API_Utils.java     ---->          # Utility functions for API calls <br>
 ┃ ┣ 📂 test <br>
 ┃ ┃ ┗ 📂 java <br>
 ┃ ┃ ┃ ┣ 📜 SmockTest.java    ---->     # Main Test Suite <br>
 ┃ ┃ ┃ ┣ 📜 Post_API_CreateUser.java   ---->  # Test Cases for POST <br>
 ┃ ┃ ┃ ┣ 📜 Get_API_GetUser.java     ---->    # Test Cases for GET <br>
 ┃ ┃ ┃ ┣ 📜 Put_API_UpdateUser.java   ---->   # Test Cases for PUT <br>
 ┃ ┃ ┃ ┗ 📜 Delete_API_DeleteUser.java  ---->  # Test Cases for DELETE <br>
 ┃ ┣ 📂 resources <br>
 ┃ ┃ ┣ 📜 testng-e2e.xml            ---->     # TestNG Suite Configuration <br>
 ┃ ┃ ┣ 📜 config.properties       ---->      # API Token storage <br>
 ┃ ┃ ┗ 📜 testData.json        ---->      # Test Data for API <br>
 ┗ 📜 pom.xml                   ---->     # Maven Configuration <br>

# Features <br>
✔ REST API Automation using REST Assured <br>
✔ Test Execution with TestNG <br>
✔ Maven Integration for Dependency Management <br>
✔ Modularized Code using API_Utils.java <br>
✔ Configurable API Token via config.properties <br>
✔ Parameterized Testing with testData.json <br>

# Notes
To add more test cases, create a new test file in src/test/java/ <br>
To modify API endpoints or tokens, update config.properties <br>
You can add test groups using @Test(groups="e2e") to control test execution <br>

# API Endpoint	Test Scenario	Test Input	Expected Status Code	Expected Result <br>
GET /users/{id}	Fetch details of an existing user	Valid user ID	200 OK	User details are returned correctly <br>
GET /users/{id}	Fetch details of a non-existing user	Non-existing user ID	404 Not Found	Error message returned <br>
GET /users/{id}	Fetch user with invalid ID	Invalid user ID format	400 Bad Request	Error message for invalid ID <br>
GET /users/{id}	Fetch deleted user	Previously deleted user ID	404 Not Found	Error message returned <br>
GET /users/{id}	Fetch user without token	No authentication token	401 Unauthorized	Error message for missing authentication <br>
GET /users/{id}	Fetch user with invalid token	Invalid authentication token	401 Unauthorized	Error message for invalid authentication <br>

POST /users	Create a new user	Valid user details	201 Created	User is created successfully <br>
POST /users	Create a user with duplicate email	Email already exists	422 Unprocessable Entity	Error message for duplicate email <br>
POST /users	Create a user without email	Missing email field	422 Unprocessable Entity	Error message for missing email <br>
POST /users	Create a user with invalid status	Invalid status value	422 Unprocessable Entity	Error message for invalid status <br>
POST /users	Create a user with invalid gender	Invalid gender value	422 Unprocessable Entity	Error message for invalid gender <br>
POST /users	Create a user without token	No authentication token	401 Unauthorized	Error message for missing authentication <br>
POST /users	Create a user with invalid token	Invalid authentication token	401 Unauthorized	Error message for invalid authentication <br>

PUT /users/{id}	Update an existing user	Valid user ID and updates	200 OK	User is updated successfully <br>
PUT /users/{id}	Update a non-existing user	Non-existing user ID	404 Not Found	Error message for non-existing user <br>
PUT /users/{id}	Update a user without token	No authentication token	401 Unauthorized	Error message for missing authentication <br>
PUT /users/{id}	Update a user with invalid token	Invalid authentication token	401 Unauthorized	Error message for invalid authentication <br>
PUT /users/{id}	Update a user with invalid status	Invalid status value	422 Unprocessable Entity	Error message for invalid status <br>
PUT /users/{id}	Update a user with invalid gender	Invalid gender value	422 Unprocessable Entity	Error message for invalid gender <br>

DELETE /users/{id}	Delete an existing user	Valid user ID	204 No Content	User is deleted successfully <br>
DELETE /users/{id}	Delete a non-existing user	Non-existing user ID	404 Not Found	Error message for non-existing user <br>
DELETE /users/{id}	Delete a user without token	No authentication token	401 Unauthorized	Error message for missing authentication <br>
DELETE /users/{id}	Delete a user with invalid token	Invalid authentication token	401 Unauthorized	Error message for invalid authentication <br>


# Contact <br>
📧 GitHub: API-Test-Automation <br>
📧 Maintainer: Anuchit Saelo <br>
