# Sprint 1

## Story points

Everybody can work 1 story point per day

* Ashley    - 11 SP/week
* Christian - 7 SP/week
* Kelvin    - 7 SP/week
* Anton     - 8 SP/week
* Jeffrey   - 8 SP/week

41 story points/week (5 day sprint)

## U1 

As Marge (counsellor), I want to bulk upload multiple filled Excel templates to the TEQ data platform so that they can be analysed and used to improve immigrant services.

### T1

Design the tables for client information in SQL Workbench.

### T2

Design the tables for data verification in SQL Workbench.

### T3

Create tables for basic client info (Unique Identifier, Address, Organization).  
Dependency: T1

### T4

Create tables which store values that are acceptable.  
Dependency: T2

### T5

Create functions/methods for checking if data in Excel templates are valid.

### T6

Read and parse the Excel files in the back-end.

### T7

Update the database with the parsed excel data.
Dependency: T3 and T4

### T8

Develop basic front-end UI for uploading Excel templates.