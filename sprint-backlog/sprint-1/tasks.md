# U1 | 49 story points
As Marge (counsellor), I want to bulk upload multiple filled Excel templates to
the TEQ data platform so that they can be analysed and used to improve immigrant
services.

## T1 | 4 story points
Design the tables for client information in SQL Workbench.
* use iCare templates to determine the design

## T2 | 3 story points
Design the tables for data verification in SQL Workbench.
* use excel files provided by client to determine which fields are mandatory and
  what entries are allowed for each field

## T3 Dependency: T1 | 3 story points
Create tables for basic client info (Unique Identifier, Address, Organization).
* use iCare templates to determine which fields need to be stored

## T4 Dependency: T2 | 3 story points
Create tables which store values that are acceptable.  
* use excel files provided by client for info about all possible values of each
  field

## T5 | 8 story points
Create functions/methods for checking if data in Excel templates are valid.
* cross reference with data from database to make sure what the client enters is
  valid

## T6 | 8 story points
Read and parse the uploaded Excel files in the back-end.
* might need to use library for reading excel files

## T7 Dependency: T3, T4, T6 | 10 story points
Insert the parsed excel data for the Client Profile template into the database.
* need to store all the different field types
* need to know the allowable values for each field

## T8 | 8 story points
Develop basic front-end UI for uploading Excel templates.
* use javafx to make the gui
