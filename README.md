# Overview

This project is a automation testing framework built with JUnit 5, Selenium, and Pickleib library, designed to support UI automation.
UI automation follows the Page Object Model for clarity and maintainability.

* BrowserType enum for easily switch browser types.
* Driver class initializes and tears down the browser.
* Each page object has its own class under main/java/pages.
* The ObjectRepository provides easy access to all pages, initializing each during the flow execution.
* UI interaction methods are used from the Pickleib library.
* All element locators are generated before the interactions, and all actions are defined in the test cases.
* StatusWatcher class is used for monitoring the test statuses.
* Screenshots taken during test failures are saved in `/screemshots` directory.
* This framework can be used in headless mode by setting the property on `test.properties` file.

## Prerequisites

- Java 17
- Maven 3.5.x
- Selenium 4.3.x 
- JUnit 5
- Pickleib 2.0.x
- Ollama Api 0.0.x


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

    ollama-url=https://i-bora.com/ollama/
    ollama-token=

## Additions

* Case 1 focuses on validating the Quality Assurance Job details, covering the requested user flow. 
* Case 2 demonstrates sending a demo request, leveraging an LLM to generate user data.
    * To enable the LLM functionality, the provided Ollama API token must be stored in the test.properties file. 
* Case 3 follows the tutorial up to and including the Web Templates on-site campaign section. 



