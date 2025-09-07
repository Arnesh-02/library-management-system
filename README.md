# 📚 Library Management System

## Introduction

The **Library Management System (LMS)** is a **console-based Java application** designed to streamline library operations for **Administrators** and **Borrowers**.

It provides features for **authentication**, **book inventory management**, **borrowing workflows**, **fine calculation**, and **report generation**. The system enforces real-world rules such as deposits, borrowing limits, and overdue penalties.

📖 For detailed specifications, see the [Library Management System.pdf](./docs/Library%20Management%20System.pdf).

---

## 📑 Table of Contents

* [Features](#features)
* [Modules](#modules)
* [Installation](#installation)
* [Usage](#usage)
* [Configuration](#configuration)
* [Examples](#examples)
* [Troubleshooting](#troubleshooting)
* [Dependencies](#dependencies)
* [License](#license)

---

## ✨ Features

* Secure **role-based authentication** (Admin & Borrower).
* Full **book inventory management** (add, update, delete, search).
* Borrowing workflow with cart and **3-book borrowing limit**.
* Automatic **fine calculation** for late/lost books or lost cards.
* **Reports** for administrators and history tracking for borrowers.

---

## 🧩 Modules

### **Module A: Authentication and Welcome Menu**

* Login using **EmailID** and **Password**.
* Display different menus based on **Admin** or **Borrower** role.

### **Module B: Book Inventory Management**

* Admin can add, modify, and delete books.
* Admin can manage Borrowers and Admin accounts.
* Search books by **Name** or **ISBN**.
* View books sorted by **Name** or **Available Quantity**.

### **Module C: Borrowing Books**

* Borrowers can checkout up to **3 books at once**.
* Books are borrowed via **Name or ISBN**.
* Must maintain a **₹500 minimum deposit**.
* Cannot borrow the same book twice simultaneously.

### **Module D: Fines & Regulations**

* Initial deposit: **₹1500** (refundable).
* Fine rules:

  * **Late return** → ₹2/day beyond 15 days (exponentially increases after 10 more days) OR **80% of book cost**, whichever is lower.
  * **Lost book** → 50% of book cost.
  * **Lost card** → ₹10.
* Borrower rules:

  * Can extend tenure **2 times consecutively**.
  * Cannot borrow duplicate books simultaneously.
  * Can extend, exchange, or report book/card as lost.

### **Module E: Reports**

* **Admin reports**:

  * Low stock books.
  * Never borrowed books.
  * Heavily borrowed books (to check condition).
  * Borrowers with outstanding books.
  * Status of book by ISBN (who borrowed & expected return date).
* **Borrower reports**:

  * Past fines (with reasons).
  * Borrow history.

---

## ⚙️ Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/library-management-system.git
   cd library-management-system
   ```

2. Compile the project using `javac`:

   ```bash
   javac -d bin src/*.java
   ```

3. Run the application:

   ```bash
   java -cp bin Main
   ```

---

## 🚀 Usage

* Run the application:

  ```bash
  java -cp bin Main
  ```
* Login as **Admin** or **Borrower**.
* Navigate using the **interactive console menu**.

---

## 🔧 Configuration

* Default **caution deposit**: `₹1500`.
* Minimum **balance requirement**: `₹500`.
* Configurable constants (e.g., deposit, fine rate, max borrow limit) are stored in a central `Config.java` file (or a `.properties` file if implemented).

---

## 📖 Examples

### Admin:

* Add new book → Enter book name, ISBN, author, and stock.
* Generate reports → Choose report option from menu.

### Borrower:

* Borrow a book → Search by ISBN, add to cart, and checkout.
* Return/extend a book → Provide ISBN and new return date.
* View history → Access borrow and fine history menu.

---

## 🛠 Troubleshooting

* **Login fails** → Check if credentials are correct in database/file.
* **Cannot borrow book** → Ensure deposit ≥ ₹500 and borrowing limit not exceeded.
* **Fine calculation errors** → Verify date format (DD/MM/YYYY).

---

## 📦 Dependencies

* Java Development Kit (JDK 8 or higher).
  
---

## 📜 License

This project is licensed under the **MIT License** – see the [LICENSE](LICENSE) file for details.
