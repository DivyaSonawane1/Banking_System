# Concurrent Banking System (Core Java)

A **console-based backend banking system** built using **Core Java** that demonstrates clean architecture, object-oriented design, custom exception handling, file-based persistence, and **thread-safe concurrent transactions**.

This project is intentionally built **without Spring or frameworks** to showcase strong fundamentals in Java backend development.

---

## ✨ Key Features

* User creation with **name and email**
* Account creation with **account type** (SAVINGS / CURRENT)
* Balance management via **deposit and withdrawal operations**
* **Fund transfer** between accounts
* **Thread-safe transactions** using locks
* **Deadlock prevention** with lock ordering
* Custom exception handling
* File-based persistence for accounts and transactions
* Multithreading simulation using `Runnable`

---

## 🏗️ Project Architecture

```
src/
└── com/bankingsystem/
    ├── model/           # Domain models (User, Account, Transaction)
    ├── service/         # Business logic
    ├── exception/       # Custom exceptions
    ├── util/            # Utilities (ID generation, logging, file handling)
    ├── multithreading/  # Concurrent transaction tasks
    └── main/            # Application entry point
```

This structure follows **separation of concerns** similar to real backend systems.

---

## 🧠 Design Decisions

### 1. Account Balance Handling

* Account balance is **NOT set during account creation**
* All balance changes go through:

  * `deposit()`
  * `withdraw()`
  * `transfer()`

This ensures:

* Proper auditing
* Thread safety
* Realistic banking behavior

### 2. Thread Safety

* Each `Account` has its own `ReentrantLock.`
* Transfers use **lock ordering** to prevent deadlocks
* Concurrent transfers are executed via multiple threads

### 3. Input Handling

* All user input is handled **only in `Main.java`** using `Scanner`
* Services remain clean and reusable

---

## 🚀 How to Run

1. Clone the repository
2. Open the project in IntelliJ / Eclipse
3. Run `Main.java`
4. Follow the console prompts to:

   * Create users
   * Create accounts
   * Deposit money
   * Perform transfers

---

## 📁 File Persistence

* Accounts are saved to `accounts.txt`
* Transactions are saved to `transactions.txt`
* Logs are written using a custom `LoggerUtil`

This simulates real backend persistence without a database.

---

## ⚠️ Custom Exceptions

* `InvalidAccountException`
* `AccountNotFoundException`
* `InsufficientBalanceException`

Used to enforce business rules and ensure robustness.

---

## 🧪 Multithreading Example

The system simulates **concurrent fund transfers** using:

* `TransactionTask implements Runnable.`
* Multiple threads operating on shared account data

This validates correctness under concurrent access.

---

## 📌 Technologies Used

* Java (Core Java)
* Collections Framework
* Concurrency (`ReentrantLock`, `Thread`, `Runnable`)
* File I/O
* OOP principles

---

## 🧑‍💻 Author

**Divya** - 
Backend Java Developer (Fresher)

---

## 📈 Future Enhancements

* Menu-driven CLI
* Input validation (email format, account type)
* JUnit test cases
* Database integration (JDBC / JPA)
* Migration to Spring Boot REST API

---

## ⭐ Why This Project?

This project demonstrates **strong Java backend fundamentals**, including:

* Clean architecture
* Thread safety
* Real-world business logic
* Scalability readiness
* And helps me showcase my understanding of Java concepts.
