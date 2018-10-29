# U2 | 23 story points
As Marge (counsellor), I want to select a template type and upload the Excel
file to the TEQ data platform, so that I can upload data for the different
services offered.

## T9 Dependency: T9 | 5 story points
Add UI to select template type and integrate with uploading UI 
* maybe think of a different system for adding and managing templates
* notify the backend of the type of template being uploaded

## T10 | 8 story points
Create allowed values for all template types, in database and the data fields in
the cols, and mandatory fields
* Needed to determine what templates can be uploaded and what data they can
  contain

## T11 | 10 story points
Input data read from input excel file into the database
* communicate with parsing/data insertion system
* transform data into the format suitable for injection into database
