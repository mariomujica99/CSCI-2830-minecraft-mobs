# Minecraft Mobs Encyclopedia

## Overview

The Minecraft Mobs Encyclopedia is an interactive GUI application that provides detailed information about mob creatures from the Minecraft universe. Users can browse through a comprehensive list of mobs, view detailed statistics and behavior information, and utilize sorting and filtering capabilities to explore the data. The application uses object-oriented principles with JavaFX.

## Technologies

- **Java 17+** - Core programming language
- **JavaFX** - GUI framework for user interface
- **Maven** - Build automation and dependency management
- **FXML** - XML-based UI markup language
- **CSS** - Styling for enhanced visual presentation

## Features

### Core Functionality

- **Comprehensive Mob Database**
  - Extensive collection of Minecraft mob creatures
  - Organized by dimension (Overworld, Nether, End)
  - Detailed statistics for each mob

- **Interactive Mob Listing**
  - Clean, scrollable list view of all mobs
  - Click-to-view detailed information
  - Visual feedback for user interactions
  - Responsive grid/list layout

- **Detailed Mob Information**
  - **Health Points** - Total health of the mob
  - **Damage Output** - Attack damage dealt
  - **Size Dimensions** - Physical size of the mob
  - **Behavior Patterns** - Hostile, neutral, or passive classification
  - **Spawn Locations** - Where the mob naturally appears
  - **General Description** - Overview of mob characteristics

- **Advanced Sorting Capabilities**
  - Sort by Health (ascending/descending)
  - Sort by Damage (ascending/descending)
  - Sort by Size (ascending/descending)
  - Dynamic re-ordering of mob list

- **Dimension Filtering**
  - Filter by Overworld mobs
  - Filter by Nether mobs
  - Filter by End mobs
  - View all mobs simultaneously
  - Combine filtering with sorting

- **Navigation System**
  - Seamless transition between main view and detail view
  - Back navigation from detail pages
  - Persistent filter and sort preferences

## Screenshots

### Application Launch
<img width="592" height="350" alt="Application Launch Screen" src="https://github.com/user-attachments/assets/d7603470-5d0f-4bc8-a8d4-3eb972b9a4f1" />

*The welcome screen when users first open the application.*

### Complete Mob Listing
<img width="592" height="350" alt="Complete Mob List" src="https://github.com/user-attachments/assets/a1dfd5e6-e90d-4c7f-9813-c0a747bcb79b" />

*List view showing all available mob creatures.*

### Mob Detail View
<img width="592" height="350" alt="Mob Detail Page" src="https://github.com/user-attachments/assets/ec69bcb7-aa85-4706-b376-72bb2c37148b" />

*Detailed information page displaying mob statistics, behavior, spawn locations, and description.*

### Sorting Functionality
<img width="592" height="350" alt="Sorted Mobs" src="https://github.com/user-attachments/assets/1b4cc7c8-5a37-405d-848a-5c1355a68658" />

*Mobs can be dynamically sorted by damage, health, and size for easy comparison.*

### Dimension Filtering
<img width="592" height="350" alt="Dimension Filter" src="https://github.com/user-attachments/assets/a906b360-4b6b-4f8e-926c-f4abd80d71bb" />

*Filter mobs by their native dimension (Overworld, Nether, or End).*

### Combined Filtering and Sorting
<img width="592" height="350" alt="Filter and Sort Combined" src="https://github.com/user-attachments/assets/5b2c5d54-9096-413d-b5ae-b09b72c6ea2c" />

*View showing Overworld mobs filtered and sorted by damage output.*


### Key Object-Oriented Engineering Principles

#### 1. Inheritance Hierarchies
```
Mob (Base Class)
├── HostileMob
│   ├── Zombie
│   ├── Skeleton
│   ├── Creeper
│   └── [Other hostile mobs]
├── PassiveMob
│   ├── Cow
│   ├── Pig
│   ├── Chicken
│   └── [Other passive mobs]
└── NeutralMob
    ├── Enderman
    ├── ZombifiedPiglin
    └── [Other neutral mobs]
```
*Example hierarchy structure - adjust based on your actual implementation*

#### 2. Encapsulation
- Private fields with public getter/setter methods
- Data validation within mob objects
- Protected access to mob statistics
- Centralized data management

#### 3. Polymorphism
- Common `Mob` interface/base class for all mob types
- Overridden methods for behavior-specific implementations
- Dynamic method dispatch for mob-specific actions

#### 4. Abstraction
- Abstract mob properties exposed through interfaces
- Implementation details hidden from UI layer
- Generic sorting/filtering algorithms work with any mob type

### Data Management

#### Sorting Algorithms
- **Comparator Pattern**: Custom comparators for health, damage, and size
- **Flexible Sorting**: Ascending and descending order support
- **Efficient Implementation**: Utilizes Java Collections framework

#### Filtering System
- **Predicate-Based Filtering**: Lambda expressions for dimension filtering
- **Chainable Filters**: Combine multiple filter criteria
- **Stream API Integration**: Modern Java functional programming

## Usage

### Browsing Mobs

1. **Launch**
   - The main view displays all available mobs

2. **View Mob Details**
   - Click on any mob name or card to view detailed information
   - Detail page shows health, damage, size, behavior, spawn locations, and description
   - Use the back button to return to main view

### Sorting Mobs

1. **Select Sort Criteria**
   - Choose from Health, Damage, or Size dropdown/buttons
   - Toggle between ascending and descending order

2. **View Sorted Results**
   - Mob list automatically updates with new order
   - Current sort criteria displayed in UI

### Filtering by Dimension

1. **Select Dimension Filter**
   - Choose Overworld, Nether, End, or All Dimensions
   - Filter buttons/dropdown available in main view

2. **View Filtered Results**
   - Only mobs from selected dimension are displayed
   - Filter can be combined with sorting

## Learning Outcomes

### Software Engineering Concepts

1. **Data-Driven Application Design**
   - Separation of data from presentation logic
   - Flexible data structures for easy expansion

2. **User Interface Development**
   - JavaFX scene graph and controls
   - Event handling and user interaction
   - Responsive layout design

3. **Algorithm Implementation**
   - Sorting algorithms and comparators
   - Filtering with predicates and streams
   - Efficient data manipulation

4. **Code Organization**
   - Package structure and modularity
   - Clear naming conventions
   - Separation of concerns

### Technical Skills

- JavaFX GUI programming
- FXML for declarative UI design
- CSS styling for JavaFX applications
- Event-driven programming paradigm
- Java Collections Framework mastery
- Lambda expressions and functional programming
- Maven build system configuration

## Academic Integrity Notice

This project is submitted as academic work for CSCI 2830: Object-Oriented Software Engineering. Please do not copy or reproduce for academic submissions. This is an educational project and is not affiliated with or endorsed by Mojang Studios or Microsoft.
