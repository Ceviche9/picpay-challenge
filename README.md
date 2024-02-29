# Simplified PicPay - Java Challenge with Spring Boot

This repository contains the implementation of a simplified PicPay system, allowing money transfers between regular users and merchants. Below are the requirements and implementation details of the challenge.

## Requirements

- Registration of regular users and merchants, containing Full Name, CPF/CNPJ, email, and password. CPF/CNPJ and emails must be unique in the system.
- Money transfer between regular users and between users with merchants.
- Merchants can only receive transfers, cannot send money.
- Validation of balance before transfer.
- Consultation to an external authorization service before finalizing the transfer.
- Notification of payment receipt for users and merchants, with the notification service being able to be unavailable/unstable.

## Implementation

- The application is developed in Java using the Spring Boot framework, following RESTFul standards to create an API for managing money transfers.
- The transfer operation is treated as a transaction, reverting in case of inconsistency and returning the money to the sender.
- The notification service is simulated through mocks, accessible through specific URLs.

## Example Payload

- Endpoint: `POST /transaction`

```json
{
    "value": 100.0,
    "payer": 4,
    "payee": 15
}

```

## Final Considerations
This project was developed as part of a technical challenge in Java with Spring Boot and aims to implement the proposed requirements in a simple and functional way.
