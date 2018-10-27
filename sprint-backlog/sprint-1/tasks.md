# U1 
As Marge (counsellor), I want to bulk upload multiple filled Excel templates to
the TEQ data platform so that they can be analysed and used to improve immigrant
services.

## T1
Design the tables for client information in SQL Workbench.
* use iCare templates to determine the design

## T2
Design the tables for data verification in SQL Workbench.
* use excel files provided by client to determine which fields are mandatory and
  what entries are allowed for each field

## T3 Dependency: T1
Create tables for basic client info (Unique Identifier, Address, Organization).
* use iCare templates to determine which fields need to be stored

## T4 Dependency: T2
Create tables which store values that are acceptable.  
* use excel files provided by client for info about all possible values of each
  field

## T5
Create functions/methods for checking if data in Excel templates are valid.
* cross reference with data from database to make sure what the client enters is valid

## T6
Read and parse the Excel files in the back-end.
* might need to use library for reading excel files

## T7 Dependency: T3 and T4
Update the database with the parsed excel data. 

## T8
Develop basic front-end UI for uploading Excel templates.
* use javafx to make the gui
