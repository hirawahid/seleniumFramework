# Selenium Test Automation Framework

This project is a Selenium-based test automation framework designed to test web applications. It uses Java, Maven, TestNG, and Cucumber for behavior-driven development (BDD). The framework integrates with Allure for reporting and supports parallel execution.

## Project Structure

### `src/main/java`
- **`selenium.base`**: Contains base classes for driver management and common utilities.
  - `DriverManager.java`: Manages WebDriver initialization, configuration, and cleanup.
- **`selenium.pages`**: Contains Page Object Model (POM) classes for different pages.
  - `LoginPage.java`: Handles interactions with the login page.
  - `DashboardPage.java`, `AdminPage.java`, `ForgotLoginPage.java`: Other page classes for specific functionalities.
- **`selenium.utils`**: Contains utility classes like `ConfigReader` for reading configuration properties.

### `src/test/resources`
- **`features`**: Contains Cucumber feature files.
  - `login.feature`: Defines scenarios for valid and invalid login functionality.

### `src/main/java/selenium/runners`
- **`CucumberTestRunner.java`**: The main test runner class for executing Cucumber tests with TestNG.

## Key Features

### 1. **Page Object Model (POM)**
   - Each page is represented as a class with methods to interact with its elements.
   - Example: `LoginPage.java` provides methods like `enterUsername`, `enterPassword`, and `submitForm`.

### 2. **Cucumber BDD**
   - Feature files define test scenarios in plain English.
   - Step definitions map feature steps to Java methods.

### 3. **Driver Management**
   - `DriverManager.java` handles WebDriver initialization for Chrome, Firefox, and Selenium Grid.
   - Supports headless execution and parallel testing.

### 4. **Allure Reporting**
   - Annotated with `@Step` for detailed reporting.
   - Automatically attaches screenshots for failed tests.

### 5. **TestNG Integration**
   - Supports parallel execution using TestNG's `@DataProvider`.
   - Lifecycle methods (`@BeforeMethod`, `@AfterMethod`) manage setup and teardown.

## Configuration

### `config.properties`
- Located in the `resources` folder.
- Contains key-value pairs for configuration, such as:
  - `baseUrl`: The base URL of the application.
  - `implicitWaitTimeout`: Timeout for implicit waits.

### Supported Browsers
- Chrome
- Firefox
- Selenium Grid (Chrome/Firefox)

## How to Run

### Prerequisites
- Java 11 or higher
- Maven
- ChromeDriver and GeckoDriver installed or managed via WebDriverManager.

### Steps
1. Clone the repository.
2. Update `config.properties` with the correct `baseUrl` and other settings.
3. Run the tests:
   - **Using Maven**:
     ```bash
     mvn clean test -Dbrowser=chrome
     ```
   - **Using TestNG**:
     Execute `CucumberTestRunner` from your IDE.

### Parallel Execution
- Enabled via TestNG's `@DataProvider` in `CucumberTestRunner`.

## Reporting
- Allure reports are generated automatically.
- To view the report:
  ```bash
  allure serve target/allure-results
##Actions
You can run github action test job to check results
