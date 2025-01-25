CREATE TABLE Korisnik
(
  userId INT NOT NULL,
  username VARCHAR(20) NOT NULL,
  password VARCHAR(20) NOT NULL,
  PRIMARY KEY (userId)
);
 
CREATE TABLE PoslovniKorisnik
(
  userId INT NOT NULL,
  PRIMARY KEY (userId),
  FOREIGN KEY (userId) REFERENCES Korisnik(userId)
);
 
CREATE TABLE PrivatniKorisnik
(
  userId INT NOT NULL,
  PRIMARY KEY (userId),
  FOREIGN KEY (userId) REFERENCES Korisnik(userId)
);
 
CREATE TABLE OrgProfil
(
  companyId INT NOT NULL,
  companyName VARCHAR(50) NOT NULL,
  companyDes VARCHAR(400),
  companyWeb VARCHAR(100),
  companyAddress VARCHAR(200),
  companyPhone VARCHAR(20),
  companyLogo VARCHAR(100) NOT NULL,
  userId INT NOT NULL,
  PRIMARY KEY (companyId),
  FOREIGN KEY (userId) REFERENCES PoslovniKorisnik(userId)
);
 
CREATE TABLE Igra
(
  gameName VARCHAR(200) NOT NULL,
  description VARCHAR(500),
  maxPlayerCount INT,
  gameId INT NOT NULL,
  availability VARCHAR(20) NOT NULL,
  isHomebrew BOOLEAN NOT NULL,
  requiresForm BOOLEAN NOT NULL,
  startTimestamp VARCHAR(200),
  estimatedLength VARCHAR(100) NOT NULL,
  complexity VARCHAR(100) NOT NULL,
  communicationChannel VARCHAR(100),
  pravilnik VARCHAR(50) NOT NULL,
  applicationRequired BOOLEAN NOT NULL,
  userId INT NOT NULL,
  PRIMARY KEY (gameId),
  FOREIGN KEY (userId) REFERENCES Korisnik(userId)
);
 
CREATE TABLE LokaliziranaIgra
(
  realLat FLOAT NOT NULL,
  realLng FLOAT NOT NULL,
  centerLat FLOAT NOT NULL,
  centerLng FLOAT NOT NULL,
  gameId INT NOT NULL,
  PRIMARY KEY (gameId),
  FOREIGN KEY (gameId) REFERENCES Igra(gameId)
);
 
CREATE TABLE TocnoLokacijskaIgra
(
  exactLat FLOAT NOT NULL,
  exactLng FLOAT NOT NULL,
  gameId INT NOT NULL,
  PRIMARY KEY (gameId),
  FOREIGN KEY (gameId) REFERENCES Igra(gameId)
);
 
CREATE TABLE OnlineIgra
(
  timezone VARCHAR(50) NOT NULL,
  gameId INT NOT NULL,
  PRIMARY KEY (gameId),
  FOREIGN KEY (gameId) REFERENCES Igra(gameId)
);
 
CREATE TABLE Prijava
(
  status VARCHAR(20) NOT NULL,
  prijavaGameId INT NOT NULL,
  prijavaUserId INT NOT NULL,
  PRIMARY KEY (prijavaGameId, prijavaUserId),
  FOREIGN KEY (prijavaGameId) REFERENCES Igra(gameId),
  FOREIGN KEY (prijavaUserId) REFERENCES PrivatniKorisnik(userId)
);
 
CREATE TABLE Pitanje
(
  questionText VARCHAR(500) NOT NULL,
  pitanjeGameId INT NOT NULL,
  PRIMARY KEY (questionText, pitanjeGameId),
  FOREIGN KEY (pitanjeGameId) REFERENCES Igra(gameId)
);
 
CREATE TABLE Odgovor
(
  answerText VARCHAR(500) NOT NULL,
  questionText VARCHAR(500) NOT NULL,
  pitanjeGameId INT NOT NULL,
  prijavaGameId INT NOT NULL,
  prijavaUserId INT NOT NULL,
  PRIMARY KEY (answerText, questionText, pitanjeGameId, prijavaGameId, prijavaUserId),
  FOREIGN KEY (questionText, pitanjeGameId) REFERENCES Pitanje(questionText, pitanjeGameId),
  FOREIGN KEY (prijavaGameId, prijavaUserId) REFERENCES Prijava(prijavaGameId, prijavaUserId)
);
ALTER TABLE Prijava
ADD CONSTRAINT chk_status CHECK (status IN ('Accepted', 'Denied', 'Waiting'));
ALTER TABLE Igra
ADD CONSTRAINT chk_availability CHECK (availability IN ('private, public'));
