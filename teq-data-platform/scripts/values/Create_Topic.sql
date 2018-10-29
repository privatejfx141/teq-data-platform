CREATE TABLE Topic (
    id CHAR(3) PRIMARY KEY NOT NULL,
    description VARCHAR(255) NOT NULL
);

INSERT INTO Topic
    (id, description)
VALUES
	('OCA', 'Overview of Canada'),	
	('OCR', 'Overview of Canada Referrals'),	
	('SOI', 'Sources of Information'),
	('SIR', 'Sources of Information Referrals'),	
	('RIF', 'Rights and Freedoms'),
	('RFR', 'Rights and Freedoms Referrals'),	
	('CLJ', 'Canadian Law and Justice'),	
	('CJR', 'Canadian Law and Justice Referrals'),	
	('IDI', 'Important Documents'),	
	('IDR', 'Important Documents Referrals'),	
	('IEF', 'Improving English or French'),	
	('EFR', 'Improving English or French Referrals'),	
	('EAI', 'Employment and Income'),	
	('EIR', 'Employment and Income Referrals'),	
	('EDU', 'Education'),	
	('EDR', 'Education Referrals'),	
	('HOU', 'Housing'),	
	('HOR', 'Housing Referrals'),	
	('HEA', 'Health'),	
	('HER', 'Health Referrals'),	
	('MAF', 'Money and Finances'),	
	('MFR', 'Money and Finances Referrals'),	
	('TRA', 'Transportation'),
	('TRR', 'Transportation Referrals'),	
	('CAM', 'Communications and Media'),	
	('CMR', 'Communications and Media Referrals'),	
	('CEN', 'Community Engagement'),	
	('CER', 'Community Engagement Referrals'),	
	('BCC', 'Becoming a Canadian Citizen'),	
	('BCR', 'Becoming a Canadian Citizen Referrals'),	
	('ICO', 'Interpersonal Conflict'),
	('ICR', 'Interpersonal Conflict Referrals');