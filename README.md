# Structured Selenium Automation

## 🧾 Overview

Structured Selenium Automation is a robust test automation framework using Selenium WebDriver, TestNG, and Java, built for scalable and maintainable web UI testing. It follows the Page Object Model (POM), integrates with Docker and Jenkins for CI/CD, and generates rich reports to visualize test results.

---

## 🚀 Key Features

- ✅ Page Object Model (POM)
- 🧪 TestNG framework
- 🐳 Docker & Jenkins integration
- 📊 HTML & ExtentReports reporting
- 🧩 Utility libraries for browser config, logging, etc.
- ♻️ Cross-browser & parallel execution support

---

## 🛠️ Tech Stack

| Tool              | Version        |
|-------------------|----------------|
| Java              | 21             |
| Selenium          | 4.28.1         |
| TestNG           | 7.11.0         |
| Maven            | 3.13.0         |
| Docker           | Latest         |
| Jenkins          | Pipeline       |
| Logback          | 1.5.16         |
| WebDriverManager | 5.9.3          |
| Jackson          | 2.18.2         |
| Extent Reports | 5.x (Optional) |

---

## 📁 Project Structure

```plaintext
Structured-Selenium-Automation/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/
│   │   │       └── example/
│   │   │           └── pages/
│   │   │               ├── AbstractPage.java
│   │   │               ├── flightreservation/
│   │   │               │   ├── FlightConfirmationPage.java
│   │   │               │   ├── FlightSearchPage.java
│   │   │               │   ├── FlightSelectionPage.java
│   │   │               │   ├── RegistrationConfirmationPage.java
│   │   │               │   └── RegistrationPage.java
│   │   │               └── vendorsportal/
│   │   │                   ├── DashboardPage.java
│   │   │                   └── LoginPage.java
│   │   └── resources/
│   │       └── logback.xml
│   └── test/
│       ├── java/
│       │   ├── listener/
│       │   │   └── TestListener.java
│       │   ├── util/
│       │   │   ├── Config.java
│       │   │   ├── Constants.java
│       │   │   ├── JsonUtil.java
│       │   │   └── ResourceLoader.java
│       │   └── org/example/tests/
│       │       ├── AbstractTest.java
│       │       ├── flightreservation/
│       │       │   ├── FlightReservationTest.java
│       │       │   └── model/
│       │       │       └── FlightReservationTestData.java
│       │       └── vendorportal/
│       │           ├── VendorPortalTest.java
│       │           └── model/
│       │               └── VendorPortalTestData.java
│       └── resources/
│           ├── test-data/
│           │   ├── config/
│           │   │   └── default.properties
│           │   ├── flight-reservation/
│           │   │   └── passenger-*.json
│           │   └── vendor-portal/
│           │       └── *.json
│           └── test-suites/
│               ├── flight-reservation.xml
│               └── vendor-portal.xml
├── docker-compose/
│   ├── grid-test-suite/
│   ├── jenkins/
│   └── scale-browser/
├── Dockerfile
├── Jenkinsfile
├── pom.xml
└── runner.sh
```

---

## 🖥️ Setup Instructions

### ⚙️ Prerequisites

- JDK 11 or later
- Maven 3.x
- Docker (for Grid support)
- Git
- Browser drivers (or run in Docker)

### 🔧 Clone and Build

```bash
git clone https://github.com/shield75/Structured-Selenium-Automation.git
cd Structured-Selenium-Automation
mvn clean install
```

---

## 🧪 Running Tests

### 🔹 Local Run

```bash
mvn test
```

### 🔹 Using Shell Script

```bash
sh runner.sh chrome
```

### 🔹 Dockerized Selenium Grid

```bash
docker-compose -f docker-compose/docker-compose.yml up -d
```

Update your `config.properties` to use the Grid URL (`http://localhost:4444/wd/hub`).

---

## 🔄 CI/CD Integration (Jenkins)

This framework includes a Jenkinsfile for pipeline automation. Simply configure the Jenkins pipeline to use this file and trigger builds on code push.

---

## 📊 Test Reports

After test execution:

- **TestNG HTML Report**: `test-output/index.html`
- **Extent Report** (if configured): `result/extent-report.html`

### 🖼️ Sample Report Screenshot

![Allure Report Sample](https://github.com/shield75/Structured-Selenium-Automation/assets/your-screenshot.png)

> *Replace the above URL with a real image link or upload directly in GitHub.*

---

## 🧑‍💻 Contribution

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 🔐 License

This project is licensed under the MIT License.

© 2025 QA Automation Team @ Shield75. All rights reserved.

---

## 📬 Contact

For questions or support, reach out at [youremail@example.com].
