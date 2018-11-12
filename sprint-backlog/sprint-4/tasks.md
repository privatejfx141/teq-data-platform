# U1
As Marge (counsellor), I want to upload the Client Profile template to the TEQ data platform so that we have data on the client that can be analyzed and used to keep track of the client.
## T21 | assigned: Christian
Create unit-test/integration tests excel reading.

## T22 | assigned: Zifan
Create acceptance test for user story 1.

## T23 | assigned: Jeffrey
Develop the functions for parsing the iCARE templates and creating the related entites (Client, Course, Service, etc...) from the parsed data.

# U2
As Marge (cousellor), I want to select a template type and upload the Excel
file to the TEQ data platform, so that I can upload data for the different
services offered.

## T24 | Dependency: T12 | assigned: Kelvin
Develop the database insertion and selection methods for the LT course setup.

## T25 | assigned: Anton
Connect UI to the data backend code for template select and upload.

## T26 | Dependency: T24 T25 |assigned: Zifan
Create acceptance test for user story 2.

# U3
As Marge (counsellor), I want to be notified of any errors in the data that I am uploading so that I can quickly fix them to prevent incorrect conclusions.
## T27 | assigned: Christian 
Create acceptance test for user story 3.

# U4
As Carlos (coordinator), I want to see all the pre-set queries that I can
use, and some helpful info about each query so that I know what it does and what I can use it for.

## T28 | assigned: Jeffrey
Create a variety of pre-set queries that can be chosen from. Also be able to select from certain clients, or entire database.

## U5
As Carlos (coordinator), I want to generate basic reports from ISANS data
using pre-set queries so that ISANS and TEQ can view trends and improve their services.

## T29 | assigned: Jeffrey
Transform ResultSet from queries into reports with graphs and tables.
