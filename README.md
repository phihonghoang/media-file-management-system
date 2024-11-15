# Media File Management System - Prototype

## Overview

This is a prototype of a media file management system developed in Java. It allows for managing media files and producers (uploaders) while ensuring limited storage capacity. 
This application is built with a layered architecture, following the **MVC (Model-View-Controller)** pattern, and utilizes an **Event System** to handle communication between different components. 

The application is designed with the following functionalities:
- **Producer Management**: Producers can be added with unique names.
- **Media File Upload**: Supports uploading media files with size checks and unique retrieval addresses. Only valid media files associated with an existing producer can be uploaded.
- **Media File Retrieval**: Retrieve media files by type, and retrieve tags associated with each file.
- **Capacity Management**: The system ensures that the total storage capacity is not exceeded when uploading media files.
- **CRUD Operations**: Add, modify, and delete producers and media files.

## Features

- **Event System**: Implements an event-driven system to communicate between components, ensuring loose coupling.
- **MVC Pattern**: The project follows the **Model-View-Controller** design pattern for better separation of concerns and maintainability.
- **Observer Pattern**: The application implements the Observer Pattern to manage changes in the system. Specifically:
  - **Capacity Management**: An observer notifies when the storage capacity exceeds 90%.
  - **Tag Management**: Another observer tracks and notifies any changes in the tags associated with media files.
- **Persistence**: The system implements persistence functionality to save and load its state. This is achieved through two different technologies:
  - **JOS (Java Object Serialization)**: A method for serializing the application's state into a format that can be saved and later restored.
  - **JBP (Java Binary Persistence)**: A binary-based persistence method currently under development and will be available in a future update.
- **Work-in-Progress (WIP)**:
  - **GUI**: A graphical user interface (GUI).
  - **TCP/UDP Server**: Server functionality for TCP and UDP.
  - **JPB (Java Binary Persistence)**: The persistence feature using JPB.

## Requirements

- **JDK Version**: Java 17 or higher
- **Libraries Used**:
  - JUnit5 (for testing)
  - Mockito (for mocking dependencies in tests)

## Domain Logic

The core of the application revolves around managing the following entities:
- **Producer**: Each producer must have a unique name. Producers can upload media files and manage associated tags.
- **Media File**: The media files are categorized by type and are subject to storage capacity limitations.
- **Tags**: Tags can be associated with media files for categorization and searching purposes.

## Command-Line Interface (CLI)

The application features a state-based CLI interface, where users can perform the following actions:

1. **Change Mode**:
   - `:c` - Enter Insert Mode (for adding producers or media files).
   - `:d` - Enter Delete Mode (for deleting producers or media files).
   - `:r` - Enter View Mode (for viewing producers or media files).
   - `:u` - Enter Edit Mode (for modifying existing entries).
   - `:p` - Enter Persistence Mode (for saving/loading state).

2. **Insert Media/Producer**:
   - `P-Name` - Add a producer with a unique name.
   - `Media-Type P-Name [Tags] [Size] [Access Cost]` - Add a media file, specifying type, producer, tags, size, and access cost.

3. **View Media/Producer**:
   - `uploader` - Display a list of producers and their media files count.
   - `content [Type]` - Display media files of a specific type or all media files.
   - `tag [included/excluded]` - Display existing or non-existing tags.

4. **Delete Media/Producer**:
   - `[P-Name]` - Delete a producer.
   - `[Retrieval Address]` - Delete a media file.

5. **Modify Media/Producer**:
   - `[Retrieval Address]` - Increase access count for a media file.

## Simulation

The application is designed to be thread-safe. For testing purposes, the following simulation is implemented:
**Simulation**: Simulates concurrent media file insertions and deletions by multiple threads. 
A configurable number of threads perform random insertions and deletions on media files, with outputs logged to the console and handled by an observer.

## Technologies Used

- **Java 17**
- **JUnit 5** (for unit testing)
- **Mockito** (for mocking dependencies in tests)
- **Observer Design Pattern** (for capacity and tag change notifications)
- **MVC Design Pattern** (for separation of concerns)
- **Event System** (for communication between components)
- **Thread-Safety**: Simulations for concurrent actions on the business logic

## How to Run

1. Clone the repository.
2. Build the project using your preferred IDE (IntelliJ IDEA recommended).
3. Run the application through the provided main method (CLI/alternative CLI).

