# Overview

This project is a automation testing framework built with JUnit 5, Selenium, and Pickleib library, designed to support UI automation.
UI automation follows the Page Object Model for clarity and maintainability.

* BrowserType enum for easily switch browser types.
* Driver Factory initializes and tears down the browser.
* Each page object has its own class under main/java/pages.
* The ObjectRepository provides easy access to all pages, initializing each with the current Web driver.
* UI interaction methods are used from the Pickleib library.
* All element locators are generated before the interactions, and all actions are defined in the test cases.
* Screenshots taken during test failures are saved in `/screemshots` directory.

## Features

- **Selenium** for UI automation.
- **JUnit 5** for test execution.
- **Pickleib** for UI interactions.

## Prerequisites

- Java 17
- Maven 3.5.x
- Selenium 4.3.x 
- JUnit 5


## Getting Started

Clone the repository:

```bash
git clone https://github.com/egecansen/egecan_sen_case.git
cd egecan_sen_case
```


Build the project:

```bash
mvn clean install
```

Run

```bash
mvn clean test
```

## Configuration

`test.properties`

    frame-height=1080
    frame-width=1920
    maximize=true

    headless=false
    element-timeout=30000

## Additions

* Case1 is the requested user flow that checks the Quality Assurance Job details
* Case2 is sending a demo request with using an LLM to generate user data.
    * To be able to use the LLM feature, provided ollama-api token needs to be stored in the test.properties file.
* Case3 is following the tutorial until the Web Templates on-site campaign section.



