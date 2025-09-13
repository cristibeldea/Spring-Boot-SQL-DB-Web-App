# Factory Management Application

## 📌 Project Overview
This project is a **Spring Boot web application** for monitoring factories (clothing and footwear).  
The system uses a relational database with **8 tables** (including 2 linking tables):

- `Factory`
- `Employee`
- `Product`
- `Material`
- `Order`
- `Client`
- `OrderDetails`
- `ProductDetails`

---

## ⚙️ Features

### 🔹 Basic Functionalities
- User-friendly **data table interface**
- **Inline editing**: click a value, modify it, and save directly into the database
- **Row deletion**: delete button removes a row and all its directly linked fields in other tables
- **Row insertion**: "Add" button creates a new row, allowing the user to populate and save values

### 🔹 Extra Functionalities
- **Search bar with domain selector**:  
  - Allows searching in a specific domain  
  - Displays only entities that contain the search query  
  - If no results are found, an error message is shown  
- **Deletion confirmation popup**:  
  - Confirmation with "Yes" / "No" before removal  
- **Data validation**:  
  - Phone numbers must be valid Romanian numbers  
  - Emails must contain `@`  
  - Gender fields must be valid  
  - New entities must reference valid existing entities (e.g., an employee cannot be assigned to a non-existent factory)  
- **Login system**:  
  - Access is restricted to registered users with correct password and admin privileges  
  - Error message is displayed if login fails  

---

## 🏗️ Project Structure

- **`controllers/`**  
  Contains controllers for `login.html` and `welcome.html`, using annotations like `@GetMapping` and `@PostMapping` to handle requests.

- **`entities/`**  
  Contains all database entities and helper classes for entity logic.

- **`repositories/`**  
  Includes one repository for each entity, with methods for querying the database.

- **`services/`**  
  Contains the `Verifications` class, responsible for:  
  - Validating phone numbers, email addresses, gender, etc.  
  - Ensuring new entities are linked to existing ones  

---

## 🚀 Potential Improvements
- Add **user account creation** from the login page (currently users must be manually added to the database).
- Develop a **client-facing interface** (currently only monitoring is supported).

---

## USAGE:
- Run **src\main\java\com\example\loginAndWelcome\LoginAndWelcomeApplication.java**, then open any browser and look for **http://localhost:8080/login**. An example of an existing user inside the database that also has the Admin level is **user:"admin"; password:"admin123"**. Use that to enter the administration interface. You also need to import the **exportDatabaseFabrici.bacpac** file into your own SQL Server instance via SQL Server Management Studio (SSMS).

Thank you for your interest in this project!
