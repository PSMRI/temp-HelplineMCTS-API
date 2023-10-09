# AMRIT - Mother And Child Tracking System (MCTS) Service

[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)  ![branch parameter](https://github.com/PSMRI/HelplineMCTS-API/actions/workflows/sast-and-package.yml/badge.svg)

The AMRIT - Mother And Child Tracking System (MCTS) Service is a comprehensive application designed to provide counseling and care to mothers and their children from the early stages of pregnancy until the child turns one year old. The primary objective of the MCTS Service is to ensure the well-being and health of both the mother and child during this critical period. 

## Key Features

- **Counseling and Care:** The MCTS Service offers a range of counseling and care services to beneficiaries and their families. These services are aimed at promoting maternal and child health, addressing any concerns or issues, and providing necessary guidance and support.

- **Beneficiary Management:** The MCTS Service allows for efficient management of beneficiaries and their records. Beneficiary data is sourced from the Reproductive and Child Health (RCH) portal, which serves as a central repository for mother and child information. The MCTS Service enables seamless integration with the RCH portal to retrieve and update beneficiary records.

- **Role-based Access:** The MCTS Service involves three key roles: Auxiliary Nurse Midwife (ANM), Medical Officer (MO), and Supervisor. Each role has specific responsibilities and access privileges within the system. ANMs and MOs are responsible for making outbound calls to beneficiaries, while Supervisors allocate beneficiary lists and oversee the overall operations.

- **Configurable Call Settings:** The MCTS Service allows for flexible configuration of call parameters. Supervisors can define the number of calls a beneficiary should receive within a specific duration. This feature ensures that beneficiaries receive the appropriate counseling and care based on program norms and guidelines.

- **Antenatal and Postnatal Calls:** The MCTS Service supports two types of calls: Antenatal Care (ANC) calls and Postnatal Care (PNC) calls. ANC calls are made before delivery to provide essential care and support during pregnancy, while PNC calls are made after delivery to monitor the well-being of both mother and child.

## Building from source

To build the HelplineMCTS microservice from source, follow these steps:

### Prerequisites

- Java Development Kit (JDK) 1.8
- Maven
- Redis
- Spring Boot v2
- MySQL

### Installation

1. Clone the repository to your local machine.
2. You can copy `mcts_example.properties` to `mcts_local.properties` and edit the file accordingly. The file is under `src/main/environment` folder.
2. Install the dependencies and build the module:
   - Run `mvn clean install` in the project directory.
3. Run the development server:
   - Start the Redis server.
   - Run `mvn spring-boot:run -DENV_VAR=local` in the project directory.
   - Open your browser and go to `http://localhost:8080/swagger-ui.html#!/` to access the Swagger API documentation.

## Usage

All the features of the HelplineMCTS service are exposed as REST endpoints. Refer to the Swagger API documentation for detailed information on how to use the service and interact with its functionalities.

The AMRIT HelplineMCTS module provides a comprehensive solution for managing various aspects of your application.
