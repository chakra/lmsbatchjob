DROP TABLE people IF EXISTS;

CREATE TABLE people (
SHRStaffID VARCHAR (30),
PreferredLastName VARCHAR (30),
PreferredFirstName VARCHAR (30),
ConflictGroup VARCHAR (20),
EmployeeCode VARCHAR (20),
BusinessUnit VARCHAR (40),
State VARCHAR (20),
Team VARCHAR (20),
ManagerReference VARCHAR (20),
SecondLevelManager VARCHAR (20),
RoleLocation VARCHAR (20),
EmailAddressWork VARCHAR (40),
Active BOOLEAN
)
