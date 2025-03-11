# GreenCity Automated Tests  
🧪 **Automated testing for the [GreenCity](https://www.greencity.cx.ua/#/greenCity) web application**  

## 🛠 Technologies & Stack  
- **Programming Language:** Java 17+  
- **Testing Framework:** JUnit 5 / TestNG  
- **Automation Library:** Selenium WebDriver  
- **Dependency Management:** Maven  
- **Other Tools:**  
  - WebDriverManager – for automatic WebDriver management  
  - Log4j – for logging  
  - Allure – for test reporting  
  - CI/CD: GitHub Actions

## 📋 Project Structure  
```
📂 src
 ├── 📂 main
 │   ├── 📂 utils        # Utility classes (configurations, helpers)
 │   └── 📂 pages        # Page Object Model (POM) classes
 ├── 📂 test
 │   ├── 📂 tests        # Automated test cases
 │   ├── 📂 resources    # Configuration files
 └── 📄 pom.xml / build.gradle   # Dependency management file
 ```

## 🔧 Installation & Setup  
### 1️⃣ Clone the repository  
```
git clone https://github.com/your-repository.git
cd your-repository
```
### 2️⃣ Install dependencies
Run the following command to install all necessary dependencies:
```
mvn clean install
```
### 3️⃣ Run tests
Run all tests:
```
mvn test
```
Run a specific test
```
mvn -Dtest=TestClassName test
```
### 🏗 CI/CD Integration
Tests can be executed automatically via GitHub Actions on every commit.
Test results and logs can be found in the CI/CD pipeline output.
Allure reports are generated after test execution for better visibility.

### 📊 Generate Allure Report
To view test reports in Allure, run:
```
allure serve target/allure-results
```
