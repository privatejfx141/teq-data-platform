# U2 | 13 story points
As Marge (counsellor), I want to select a template type and upload the Excel
file to the TEQ data platform, so that I can upload data for the different
services offered.

## T9 Dependency: T9 | 5 story points
Add UI to select template type and integrate with uploading UI 
* maybe think of a different system for adding and managing templates
* notify the backend of the type of template being uploaded

## T10 | 8 story points
Store all template types in database and the data fields in the cols, and
mandatory fields
* Needed to determine what templates can be uploaded and what data they can
  contain

# U3 | 10 story points
## T11 | 5 story points
Add UI for displaying status of uploaded files with errors if they occur
* communicate with parsing/data insertion system
* notify user of any error inconsistency or duplication in the data they upload

## T12 Dependency: T11 | 5 story points
Connect UI with the code for Excel data validation
* send the file path and template type to the system which can read and parse
  excel files
