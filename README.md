## 🏦 Banking Application (Core Java)

A console-based Banking Application built using Core Java concepts.
This project demonstrates strong understanding of OOP, Collections, layered architecture, and custom exception handling.

## 📌 Project Overview
The Banking Application allows users to perform basic banking operations through a console menu interface.
The system is designed using a layered architecture:

App Layer (Main class)
Service Layer (Business logic)
Repository Layer (In-memory data storage)
Domain Layer (Entity classes)
Utility Layer (Validations)
Exception Layer (Custom exceptions)

## 🚀 Features
Open Account
Deposit Money
Withdraw Money
Transfer Money
View Account Statement
List All Accounts
Search Account by Customer Name
Exit Application

##  🛠 Tech Stack
Java (Core Java)
OOP Principles
Java Collections Framework (HashMap, List)
Custom Exceptions
Enum

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
Encapsulation
Abstraction (via BankService interface)
Polymorphism (BankServiceImpl)
Separation of Concerns

### 2️⃣ Collections Framework
HashMap for storing Accounts and Customers
List for maintaining transaction history

### 3️⃣ Enum
TransactionType (DEPOSIT, WITHDRAW, TRANSFER)

### 4️⃣ Custom Exceptions
AccountNotFoundException
InsufficientFundsException
ValidationException

### 5️⃣ Input Validation
Name validation
Email validation
Amount validation
Account type validation

##  💾 Data Storage

All data is stored in memory using HashMap.

⚠️ Note: Since this project does not use a database, data will be lost when the application stops.

### ▶️ How to Run

Clone the repository
git clone <your-repository-url>
Open the project in IntelliJ IDEA / Eclipse

Run:
Main.java

Use the console menu to perform banking operations.

##  📈 Future Enhancements
Add MySQL database integration
Convert to Spring Boot REST API
Add Logging (SLF4J / Log4j)
Add Unit Testing (JUnit & Mockito)
Implement Authentication
Add File-based persistence

## 🎯 Learning Outcomes
This project demonstrates:
Strong understanding of Core Java
Ability to design layered architecture
Implementation of clean business logic
Proper exception handling
Practical use of Collections framework

⭐ If you like this project, feel free to fork and enhance it.
