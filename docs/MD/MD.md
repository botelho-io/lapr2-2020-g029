# OO Analysis

## Rational for the identification of domain classes

### Category List

**Transactions (of business)**
**Lines of Transaction**

- Transaction

**Products or services related to transactions**

- Task

**Registers (of transactions)**

- Payment

**Roles of people**

- Collaborator (Organization)
- Manager (Organization)
- Administrator (T4J)
- Freelancer

**Places**

- App

**Events**

- PaymentScheduler
- EmailScheduler

**Physical objects**
**Specifications or descriptions**

- TaskExecutionDetails

**Catalogs**
**Groups**
**Elemnts of groups**
**Organization**

- Organization
- T4J

**Other systems (external)**

- MonetaryUnitConversionAPI
- EmailAPI
- PaymentAPI

**Regists (financial), of jobs, contacts, legal documents**
**Financial instruments**
**Referenced documents**

- HistoricalTransactionFile



## **Rational about the identification of associations between classes**

|Concept (A)               |Association            |Concept (B)               |
|-------------------------:|:---------------------:|:-------------------------|
|Collaborator              |creates                |Transaction               |
|                          |creates                |Task                      |
|                          |creates                |Freelancer                |
|                          |loads                  |HistoricalTransactionFile |
|                          |acts as                |User                      |
|Manager                   |defines                |PaymentScheduler          |
|                          |acts as                |User                      |
|Transaction               |relative to            |Task                      |
|                          |to                     |Freelancer                |
|                          |uses                   |PaymentAPI                |
|                          |made on behalf of      |Organization              |
|                          |executed with          |TaskExecutionDetails      |
|TaskExecutionDetails      |refers to              |Task                      |
|HistoricalTransactionFile |records                |Transaction               |
|PaymentScheduler          |schedules payments     |Transaction               |
|Organization              |has                    |Collaborator              |
|                          |has                    |Manager                   |
|                          |uses                   |PaymentScheduler          |
|                          |publishes              |Task                      |
|                          |has                    |Transaction               |
|Task                      |executed by            |Freelancer                |
|App                       |supports               |MonetaryUnitConversionAPI |
|                          |has                    |Organization              |
|                          |supports               |EmailAPI                  |
|                          |supports               |PaymentAPI                |
|                          |has                    |Administrator             |
|                          |has                    |Freelancer                |
|                          |has                    |EmailScheduler            |
|EmailScheduler            |uses                   |EmailAPI                  |
|Administrator             |adds                   |Organization              |
|                          |acts as                |User                      |
|EmailAPI                  |e-mails                |User                      |
|                          |e-mails                |Freelancer                |



## Domain model

![MD.svg](MD.svg)
