USE Week3_MeganM;

CREATE TABLE `Role` (
	RoleID TINYINT NOT NULL,
    Name varchar(64) not null,
    PRIMARY KEY (RoleID)
);

INSERT INTO `Role` (RoleID, Name) VALUES (1, 'HR');
INSERT INTO `Role` (RoleID, Name) VALUES (2, 'Management');
INSERT INTO `Role` (RoleID, Name) VALUES (3, 'Sales');
INSERT INTO `Role` (RoleID, Name) VALUES (4, 'Delivery');

CREATE TABLE `User` (

	Username varchar(64) NOT NULL,
    Password varchar(64) NOT NULL,
    RoleID TINYINT NOT NULL,
    PRIMARY KEY (Username),
    FOREIGN KEY(RoleID) REFERENCES `Role`(RoleID)

);

INSERT INTO `User` (Username, Password, RoleID) VALUES ('HR', 'HR', 1);
INSERT INTO `User` (Username, Password, RoleID) VALUES ('Management', 'Management', 2);
INSERT INTO `User` (Username, Password, RoleID) VALUES ('Sales', 'Sales', 3);
INSERT INTO `User` (Username, Password, RoleID) VALUES ('Delivery', 'Delivery', 4);

CREATE TABLE Token (
	Username varchar(64) NOT NULL,
    Token varchar(64) NOT NULL,
    Expiry DATETIME NOT NULL,
    FOREIGN KEY (Username) REFERENCES `User`(Username)
);