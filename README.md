Reggie 

Pathfinder 2e Monster Generator

===============================================================================================================================================================================================================================================================================================================================
Overview
===============================================================================================================================================================================================================================================================================================================================
Reggie is a fully offline, secure desktop application designed to instantly generate mathematically accurate creature stat blocks for the Pathfinder 2e roleplaying game.

The core philosophy behind Reggie is that a monster generator should be more than just a calculator that scales numbers linearly. Reggie applies official PF2e scaling algorithms and dynamically redistributes ability scores, armor classes and saving throws based on specific Combat Archetypes (e.g., Brutes, Skirmishers, Spellcasters). The result is a unique, encounter-ready creature that mechanically feels like its intended role.

===============================================================================================================================================================================================================================================================================================================================
Key Features
===============================================================================================================================================================================================================================================================================================================================
Dynamic Combat Archetypes: Stat distributions are smartly assigned based on combat roles rather than generic scaling. A Level 10 Spellcaster will have vastly different physical and mental attributes than a Level 10 Brute.

Procedural Flavor: Includes a lightweight procedural generation engine to assign intimidating, tabletop-ready names to generated encounters.

VTT Integration: Native export pipeline for FoundryVTT JSON, creating nested data schemas that can be ingested directly into virtual tabletops.

Print-Ready PDFs: Built-in Apache PDFBox rendering creates gorgeous, high-contrast, black-and-white PDF stat blocks styled specifically after the official Monster Core rulebook.

Offline & Secure: Runs entirely locally with zero external network dependencies. Domain-level input validation guarantees data integrity and protects against integer overflow or out-of-bounds scaling.

===============================================================================================================================================================================================================================================================================================================================
Tech Stack
===============================================================================================================================================================================================================================================================================================================================
Core Engine: Java 21 (Immutable Records, strict domain validation)

Presentation Layer: JavaFX (Embedded WebView for HTML/CSS rendering)

Build System: Gradle (Multi-project architecture)

Export Libraries: Google Gson (JSON assembly), Apache PDFBox (Absolute X/Y PDF rendering)

===============================================================================================================================================================================================================================================================================================================================
Getting Started
===============================================================================================================================================================================================================================================================================================================================
Prerequisites
JDK 21 or higher installed.

Ensure JAVA_HOME is configured correctly.

Running the Application
Clone the repository and run the desktop UI directly via the Gradle wrapper:

Bash
git clone https://github.com/yourusername/reggie.git
cd reggie
./gradlew :desktop-ui:run
Running the Test Suite
Reggie includes a comprehensive suite of "Arrange-Act-Assert" unit tests validating the core math and security boundaries:

Bash
./gradlew :core-engine:test
Built for Game Masters who need mathematically tight encounters in seconds.
