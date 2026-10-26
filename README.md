# AquaReach System 💧

## Project Overview

AquaReach is a water distribution and donation management system designed to organize water items, manage donations, track placements, and maintain information about donors and locations.

The system provides a structured way to manage water resources, record donations, and organize the distribution process.

## Features

* **Water Item Management:** Manage different types of water items, including water bottles, water boxes, and water coolers.
* **Donation Management:** Record donations and associate them with donors and water items.
* **Donor Management:** Store donor information and manage donation records.
* **Location Management:** Organize distribution locations and track placements.
* **Water Distribution:** Manage water distribution and update available quantities.
* **Inventory Management:** Restock water items and monitor their available quantities.
* **Item Sorting:** Sort water items using comparison methods.
* **Exception Handling:** Handle situations where the requested water quantity is unavailable.

## System Components

### 1. WaterItem

An abstract class representing water items in the system.

Subclasses:

* `WaterBottle`
* `WaterBox`
* `WaterCooler`

### 2. Distributable

An interface that defines the distribution and restocking operations.

### 3. Donor

Represents donors and maintains their donation records.

### 4. Donation

Stores information about a donation, including the donated water item, quantity, and date.

### 5. Placement

Represents the placement or delivery of water items to a specific location, including the quantity, date, and placement status.

### 6. Location

Manages location information and associated placements.

### 7. AquaReachSystem

The main system class responsible for managing water items, donors, locations, donations, placements, inventory, and sorting operations.

### 8. PlacementStatus

An enumeration representing placement statuses:

* `DONATED`
* `DELIVERED`

### 9. WaterNotAvailableException

A custom exception used when the requested water quantity is unavailable.

### 10. AquaReach

The application entry point containing the `main()` method.

## Technologies and Concepts

The system's UML design demonstrates the use of object-oriented programming concepts, including:

* Classes and Objects
* Inheritance
* Abstraction
* Interfaces
* Encapsulation
* Polymorphism
* Collections
* Exception Handling
* Enumerations
* Object Relationships

## Project Structure

The project includes classes and components for:

* Water item types
* Donation and donor management
* Placement and location management
* Central system operations
* Custom exception handling
* Application execution

The actual source file organization depends on the project implementation.

## Purpose

The purpose of AquaReach is to provide an organized approach to managing water donations and distribution by maintaining water inventory, donor records, and placement information.

---

**Project Name:** AquaReach System
**Project Type:** Object-Oriented Programming Project
