# Bajaj Finserv Health Limited (BFHL) Developer Challenge REST API

A production-grade, complete Spring Boot REST API for the BFHL challenge built using **Java 21** and **Spring Boot 3**.

---

## Technical Features
- **Java 21** & **Spring Boot 3** (Parent POM `3.3.0`).
- **Clean Architecture & SOLID principles**: Modular separation of Controllers, Services, DTOs, and Exception Handling.
- **Robust Parsing & Overflow Protection**: Numbers are processed using `BigInteger` to avoid runtime precision/overflow exceptions on large integer inputs.
- **Alternating Casing & Reversing Logic**: Custom character processing logic implemented for building the output concatenation string.
- **Configuration-driven**: Personal credentials (email, roll number, and name prefix) are configurable via `application.properties`.
- **Lombok** Integration to prevent boilerplate.
- **Comprehensive Unit Testing**: Written using JUnit 5 and Spring MockMvc with high coverage.

---

## Project Structure
```text
src/main/java/com/bajaj/bfhl
├── BfhlApplication.java
├── controller
│   └── BfhlController.java
├── service
│   ├── BfhlService.java
│   └── impl
│       └── BfhlServiceImpl.java
├── dto
│   ├── request
│   │   └── BfhlRequest.java
│   └── response
│       ├── BfhlResponse.java
│       └── GetOperationResponse.java
└── exception
    ├── ErrorResponse.java
    ├── InvalidInputException.java
    └── GlobalExceptionHandler.java

src/test/java/com/bajaj/bfhl
├── controller
│   └── BfhlControllerTest.java
└── service
    └── BfhlServiceImplTest.java
```

---

## How to Run & Verify

### 1. Prerequisites
- **Java Development Kit (JDK) 21** or higher.
- **Apache Maven 3.8+** installed and set up in your system PATH.

### 2. Run Locally
To run the server locally, run the following command in the project root:
```bash
mvn spring-boot:run
```
By default, the server will start on port `8080` (url: `http://localhost:8080`).

### 3. Run Automated Tests
To run all JUnit 5 unit and integration tests:
```bash
mvn clean test
```

### 4. Build executable JAR
To package the application into a standalone executable JAR:
```bash
mvn clean package
```
The JAR will be generated inside the `target/` directory:
```text
target/bfhl-0.0.1-SNAPSHOT.jar
```
To run the built JAR:
```bash
java -jar target/bfhl-0.0.1-SNAPSHOT.jar
```

---

## API Documentation & Verification

### 1. POST Endpoint
- **URL**: `http://localhost:8080/bfhl`
- **Method**: `POST`
- **Content-Type**: `application/json`

#### Request Payload Example:
```json
{
  "data": ["a", "1", "334", "4", "R", "$"]
}
```

#### Response Example (HTTP 200 OK):
```json
{
  "is_success": true,
  "user_id": "harshit_nijhawan_24062026",
  "email": "harshit.nijhawan2021@vitstudent.ac.in",
  "roll_number": "21BCE1234",
  "odd_numbers": ["1"],
  "even_numbers": ["334", "4"],
  "alphabets": ["A", "R"],
  "special_characters": ["$"],
  "sum": "339",
  "concat_string": "Ra"
}
```

#### Test via PowerShell:
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/bfhl" -Method Post -Body '{"data": ["a", "1", "334", "4", "R", "$"]}' -ContentType "application/json" | ConvertTo-Json
```

#### Test via cURL:
```bash
curl -X POST http://localhost:8080/bfhl \
     -H "Content-Type: application/json" \
     -d '{"data": ["a", "1", "334", "4", "R", "$"]}'
```

---

### 2. GET Endpoint
- **URL**: `http://localhost:8080/bfhl`
- **Method**: `GET`

#### Response Example (HTTP 200 OK):
```json
{
  "operation_code": 1
}
```

#### Test via PowerShell:
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/bfhl" -Method Get | ConvertTo-Json
```

#### Test via cURL:
```bash
curl -X GET http://localhost:8080/bfhl
```

---

## Deployment Instructions

### 1. Pushing to GitHub
1. Create a repository on GitHub (e.g. `bajaj-bfhl-challenge`).
2. Open terminal in project root and run:
   ```bash
   git init
   git add .
   git commit -m "Initial commit: Bajaj BFHL REST API"
   git branch -M main
   git remote add origin https://github.com/<your-github-username>/<your-repo-name>.git
   git push -u origin main
   ```

### 2. Deploying on Render
1. Log in to [Render](https://render.com/).
2. Click **New +** and select **Web Service**.
3. Connect your GitHub repository.
4. Configure the Web Service:
   - **Name**: `bajaj-bfhl-api`
   - **Environment**: `Docker` or `Java`
     - *Using Java Environment*:
       - **Runtime**: `Java` (Select Java 21)
       - **Build Command**: `./mvnw clean install -DskipTests` or `mvn clean install -DskipTests`
       - **Start Command**: `java -jar target/bfhl-0.0.1-SNAPSHOT.jar`
   - **Plan**: `Free`
5. Click **Deploy Web Service**.
6. Note down the public URL provided by Render (e.g. `https://bajaj-bfhl-api.onrender.com`).

---

## Verify Hosted Endpoint
Once deployed, test your public URL:
1. **GET Request**: `https://<your-app-name>.onrender.com/bfhl`
2. **POST Request**: Send the JSON payload to `https://<your-app-name>.onrender.com/bfhl` using Postman, cURL, or a frontend client.
