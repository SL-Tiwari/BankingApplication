## 🏦 Banking Application (Core Java)

A console-based Banking Application built using Core Java concepts.
This project demonstrates strong understanding of OOP, Collections, layered architecture, and custom exception handling.

## 📌 Project Overview
The Banking Application allows users to perform basic banking operations through a console menu interface.
The system is designed using a layered architecture:

a. App Layer (Main class)
b. Service Layer (Business logic)
c. Repository Layer (In-memory data storage)
d. Domain Layer (Entity classes)
e. Utility Layer (Validations)
f. Exception Layer (Custom exceptions)

## 🚀 Features
1. Open Account
2. Deposit Money
3. Withdraw Money
4. Transfer Money
5. View Account Statement
6. List All Accounts
7. Search Account by Customer Name
8. Exit Application

##  🛠 Tech Stack
1. Java (Core Java)
2. OOP Principles
3. Java Collections Framework (HashMap, List)
4. Custom Exceptions
5. Enum

##  📂 Project Structure
banking-application/
│
├── app/
│ └── Main.java
│
├── domain/
│ ├── Account.java
│ ├── Customer.java
│ ├── Transaction.java
│ └── Type.java
│
├── service/
│ ├── BankService.java
│ └── BankServiceImpl.java
│
├── repository/
│ ├── AccountRepository.java
│ ├── CustomerRepository.java
│ └── TransactionRepository.java
│
├── util/
│ └── validation.java
│
└── exception/
├── AccountNotFoundException.java
├── InsufficientFundsException.java
└── ValidationException.java

## 🧠 Concepts Implemented

### 1️⃣ Object-Oriented Programming
a. Encapsulation
b. Abstraction (via BankService interface)
c. Polymorphism (BankServiceImpl)
d. Separation of Concerns

### 2️⃣ Collections Framework
a. HashMap for storing Accounts and Customers
b. List for maintaining transaction history

### 3️⃣ Enum
TransactionType (DEPOSIT, WITHDRAW, TRANSFER)

### 4️⃣ Custom Exceptions
a. AccountNotFoundException
b. InsufficientFundsException
c. ValidationException

### 5️⃣ Input Validation
a. Name validation
b. Email validation
c. Amount validation
d. Account type validation

##  💾 Data Storage
All data is stored in memory using HashMap.

⚠️ Note: Since this project does not use a database, data will be lost when the application stops.

### ▶️ How to Run
a. Clone the repository
b. git clone <your-repository-url>
c. Open the project in IntelliJ IDEA / Eclipse

Run:
Main.java

Use the console menu to perform banking operations.

##  📈 Future Enhancements
1. Add MySQL database integration
2. Convert to Spring Boot REST API
3. Add Logging (SLF4J / Log4j)
4. Add Unit Testing (JUnit & Mockito)
5. Implement Authentication
6. Add File-based persistence

## 🎯 Learning Outcomes
This project demonstrates:
Strong understanding of Core Java
Ability to design layered architecture
Implementation of clean business logic
Proper exception handling
Practical use of Collections framework

⭐ If you like this project, feel free to fork and enhance it.
