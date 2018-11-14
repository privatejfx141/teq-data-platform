
# U2
As Marge (cousellor), I want to select a template type and upload the Excel
file to the TEQ data platform, so that I can upload data for the different
services offered.

## T28 | Dependency: T26 |assigned: Zifan
Create acceptance test for user story 2.

# U3
As Marge (counsellor), I want to be notified of any errors in the data that I am uploading so that I can quickly fix them to prevent incorrect conclusions.

## T26 | assigned: Zifan
Connect UI to error checking to allow users to get a notification about the error.

# U4
As Carlos (coordinator), I want to see all the pre-set queries that I can
use, and some helpful info about each query so that I know what it does and what I can use it for.

## T29 | assigned: Jeffrey
Create a variety of pre-set queries that can be chosen from, for certain clients.

## T30 | assigned: Kelvin
Create a variety of pre-set queries that can be chosen from, for the entire database.

## T31 | Dependency: T29 T30 | assigned: Zifan
Connect UI with the preset queries to allow users to to select a preset query.

## U5
As Carlos (coordinator), I want to generate basic reports from ISANS data
using pre-set queries so that ISANS and TEQ can view trends and improve their services.

## T32 | assigned: Christian
Transform ResultSet from queries into reports with graphs and tables.

## T33 | Dependency: T32 | assigned:Zifan
Create UI to display the reports from the selected queries.
