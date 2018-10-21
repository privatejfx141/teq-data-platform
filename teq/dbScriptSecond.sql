CREATE TABLE `iCareTemplate` (
  `iCareID` VARCHAR(45) NOT NULL,
  `ClientID` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`iCareID`, `ClientID`));


CREATE TABLE `Address` (
  `AdressID` VARCHAR(16) NOT NULL,
  `StreetNumber` VARCHAR(10) NULL,
  `StreetName` VARCHAR(10) NULL,
  `StreetType` VARCHAR(10) NULL DEFAULT CURRENT_TIMESTAMP,
  `StreetDirection` VARCHAR(10) NULL,
  `Unit/Suite/Apt` VARCHAR(10) NULL,
  `City` VARCHAR(20) NULL,
  `Province` VARCHAR(20) NULL,
  `PostalCode` CHAR(6) NOT NULL,
  PRIMARY KEY (`AdressID`));

CREATE TABLE `Client` (
  `IDType` VARCHAR(46) NOT NULL,
  `ClientID` VARCHAR(45) NOT NULL,
  `DateOfBirth` DATE NOT NULL,
  `PhoneNumber` CHAR(12) NULL,
  `HaveEmail` TINYINT NULL,
  `EmailAddress` VARCHAR(45) NULL,
  `AdressID` VARCHAR(16) NOT NULL,
  `PostalCode` CHAR(6) NOT NULL,
  `OfficialLanguage` VARCHAR(23) NOT NULL,
  `FutureConsent` TINYINT NOT NULL,
  PRIMARY KEY (`ClientID`, `IDType`),
  INDEX `fk_ClientProfile_Address1_idx` (`AdressID` ASC, `PostalCode` ASC) VISIBLE,
  CONSTRAINT `fk_Needs Assesments & Referrals_iCare Template1`
    FOREIGN KEY (`IDType`)
    REFERENCES `mydb`.`iCareTemplate` (`iCareID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ClientProfile_Address1`
    FOREIGN KEY (`AdressID` , `PostalCode`)
    REFERENCES `mydb`.`Address` (`AdressID` , `PostalCode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE `CommunityConnections` (
  `IDType` VARCHAR(46) NOT NULL,
  `ClientID` VARCHAR(45) NOT NULL,
  `ActivitiesRecieved` VARCHAR(45) NOT NULL,
  `FocusOfServiceReceived` VARCHAR(45) NOT NULL,
  `ServiceStatus` VARCHAR(45) NOT NULL,
  `EssentialSkillsAndAptitudesTrainingReceived` TINYINT NOT NULL,
  PRIMARY KEY (`IDType`, `ClientID`),
  CONSTRAINT `fk_Community Connections_iCare Template1`
    FOREIGN KEY (`IDType`)
    REFERENCES `mydb`.`iCareTemplate` (`iCareID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE `Employment` (
  `iCareID` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`iCareID`),
  CONSTRAINT `fk_Employment_iCare Template1`
    FOREIGN KEY (`iCareID`)
    REFERENCES `mydb`.`iCareTemplate` (`iCareID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE `InfoAndOrientation` (
  `iCareID` VARCHAR(45) NOT NULL,
  `EssentialSkillsAndAptitudesTrainingReceived` TINYINT NOT NULL,
  `InformationReceived` TINYINT NOT NULL,
  PRIMARY KEY (`iCareID`),
  CONSTRAINT `fk_Info & Orientation_iCare Template1`
    FOREIGN KEY (`iCareID`)
    REFERENCES `mydb`.`iCareTemplate` (`iCareID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `ClientEnrol` (
  `iCare Template_idiCare Template` INT NOT NULL,
  PRIMARY KEY (`iCare Template_idiCare Template`),
  CONSTRAINT `fk_LT Client Enrol_iCare Template1`
    FOREIGN KEY (`iCare Template_idiCare Template`)
    REFERENCES `mydb`.`iCareTemplate` (`iCareID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE `CourseSetup` (
  `iCare Template_idiCare Template` INT NOT NULL,
  `CourseCode` VARCHAR(45) NOT NULL,
  `OngoingBasis` TINYINT NOT NULL,
  `OfficialLanguage` VARCHAR(45) NOT NULL,
  `FormatOfTraining` VARCHAR(45) NOT NULL,
  `NumberOfSpots` VARCHAR(10) NOT NULL,
  `NumberOfIRCCFundedSpots` VARCHAR(10) NOT NULL,
  `StudentsCanEnrol` TINYINT NOT NULL,
  `SupportServicesAvailable` TINYINT NOT NULL,
  `HoursPerClass` VARCHAR(5) NOT NULL,
  `ClassesPerWeek` VARCHAR(5) NOT NULL,
  `FocusOfCourse` VARCHAR(45) NOT NULL,
  `DirectedAtTargetGroup` TINYINT NOT NULL,
  `MaterialsUsed` VARCHAR(45) NOT NULL,
  `AdressID` VARCHAR(16) NOT NULL,
  `TelephoneNumber` VARCHAR(12) NOT NULL,
  `EmailAddress` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`iCare Template_idiCare Template`, `AdressID`),
  INDEX `fk_CourseSetup_Address1_idx` (`AdressID` ASC) VISIBLE,
  CONSTRAINT `fk_LT Course Setup_iCare Template1`
    FOREIGN KEY (`iCare Template_idiCare Template`)
    REFERENCES `mydb`.`iCareTemplate` (`iCareID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CourseSetup_Address1`
    FOREIGN KEY (`AdressID`)
    REFERENCES `mydb`.`Address` (`AdressID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE `ClientExit` (
  `iCare Template_idiCare Template` INT NOT NULL,
  `CourseCode` VARCHAR(45) NOT NULL,
  `ClientTrainingStatus` VARCHAR(45) NOT NULL,
  `CertificateIssued` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`iCare Template_idiCare Template`),
  CONSTRAINT `fk_LT Client Exit_iCare Template`
    FOREIGN KEY (`iCare Template_idiCare Template`)
    REFERENCES `mydb`.`iCareTemplate` (`iCareID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE `NeedsAssesment` (
  `AssesmentID` VARCHAR(45) NOT NULL,
  `StartDate` DATE NOT NULL,
  `BecomeCanadianCitizen` TINYINT NOT NULL,
  `SupportServiceRequired` TINYINT NOT NULL,
  `NonIRCCServicesNeeded` TINYINT NOT NULL COMMENT 'NonIRRCCServicesN\needed',
  `PlanCompleted` TINYINT NOT NULL,
  `EndDate` DATE NOT NULL,
  PRIMARY KEY (`AssesmentID`),
  CONSTRAINT `fk_Employment_iCare Template10`
    FOREIGN KEY (`AssesmentID`)
    REFERENCES `mydb`.`iCareTemplate` (`iCareID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE `Service` (
  `ServiceID` INT NOT NULL,
  `PostalCodeOfService` CHAR(6) NOT NULL,
  `LanguageOfService` VARCHAR(45) NOT NULL,
  `ReferredBy` VARCHAR(45) NOT NULL,
  `StartDate` DATE NOT NULL,
  `SupportServiceReceived` TINYINT NOT NULL,
  `TypeOfInstitution` VARCHAR(45) NOT NULL,
  `LanguageOfPreference` VARCHAR(45) NOT NULL,
  `InfoAndOrientation_iCareID` VARCHAR(45) NOT NULL,
  `CommunityConnections_IDType` VARCHAR(46) NOT NULL,
  `CommunityConnections_ClientID` VARCHAR(45) NOT NULL,
  `Employment_iCareID` VARCHAR(45) NOT NULL,
  `NeedsAssesment_AssesmentID` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ServiceID`),
  INDEX `fk_Service_InfoAndOrientation1_idx` (`InfoAndOrientation_iCareID` ASC) VISIBLE,
  INDEX `fk_Service_CommunityConnections1_idx` (`CommunityConnections_IDType` ASC, `CommunityConnections_ClientID` ASC) VISIBLE,
  INDEX `fk_Service_Employment1_idx` (`Employment_iCareID` ASC) VISIBLE,
  INDEX `fk_Service_NeedsAssesment1_idx` (`NeedsAssesment_AssesmentID` ASC) VISIBLE,
  CONSTRAINT `fk_Service_InfoAndOrientation1`
    FOREIGN KEY (`InfoAndOrientation_iCareID`)
    REFERENCES `mydb`.`InfoAndOrientation` (`iCareID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Service_CommunityConnections1`
    FOREIGN KEY (`CommunityConnections_IDType` , `CommunityConnections_ClientID`)
    REFERENCES `mydb`.`CommunityConnections` (`IDType` , `ClientID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Service_Employment1`
    FOREIGN KEY (`Employment_iCareID`)
    REFERENCES `mydb`.`Employment` (`iCareID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Service_NeedsAssesment1`
    FOREIGN KEY (`NeedsAssesment_AssesmentID`)
    REFERENCES `mydb`.`NeedsAssesment` (`AssesmentID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);