# Transaction Project

## Overview
This is a simple banking transaction management application developed using Spring Boot. It offers basic Create, Read, Update, and Delete (CRUD) operations for transactions.

## Structure
- **Controller Layer**: Responsible for handling HTTP requests and generating responses.
- **Service Layer**: Implements the core business logic for transaction management.
- **Model Layer**: Defines the data model, such as the `Transaction` class.
- **Exception Layer**: Handles custom exceptions, like `TransactionNotFoundException`.

## Tech Stack
- **Spring Boot**: A framework for building standalone, production-grade Spring applications.
- **Maven**: A build automation tool for managing project dependencies and building the project.
- **Lombok**: A library that simplifies Java code by automatically generating getters, setters, etc.

## Prerequisites
- **JDK 21**: Ensure Java Development Kit 21 is installed.
- **Maven**: Version 3.x or higher.

## Installation and Running

### Clone the Repository
```bash
git clone <repository-url>
cd <project-directory>
