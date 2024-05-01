# Wine Tasting Database
SER322 - Group 1
Keenan High
Adam Rojas
Levi Barrett
Rodrigo Cordero

LINK TO DEMO VIDEO: https://youtu.be/f3QVbhL5U38

Steps for creating the database
Create a SQL Schema (with MySQL Workbench, for example) and have at hand the following information:
Username
Password
Database URL. For example: “jdbc:mysql://localhost:3306/wine”

For our example we made a database named 'wine'. 

Then add these create statements for the tables:

CREATE TABLE WINE
(Varietal VARCHAR(30) NOT NULL,
Vintage INT NOT NULL,
Price FLOAT NOT NULL,
CHECK (Price > 0),
PRIMARY KEY (Varietal, Vintage));

CREATE TABLE CUSTOMER
(CustomerId INT NOT NULL,
FirstName VARCHAR(30) NOT NULL,
LastName VARCHAR(30) NOT NULL,
PRIMARY KEY (CustomerId));

CREATE TABLE LOCATION
(LocationId INT NOT NULL,
Name VARCHAR(30) NOT NULL,
Address VARCHAR(100),
PRIMARY KEY (LocationId));

CREATE TABLE TRANSACTIONS
(TransactionId INT NOT NULL,
Date DATE NOT NULL,
Total INT,
CustomerId INT,
LocationId INT,
PRIMARY KEY (TransactionId),
CONSTRAINT TRANSACTIONS_CUSTOMERID
FOREIGN KEY (CustomerId) REFERENCES CUSTOMER (CustomerId)
ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT TRANSACTIONS_LOCATIONID
FOREIGN KEY (LocationId) REFERENCES LOCATION (LocationId)
ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE DELIVERY
(DeliveryId INT NOT NULL,
Date DATE NOT NULL,
From_Id INT NOT NULL,
To_Id INT NOT NULL,
PRIMARY KEY (DeliveryId),
CONSTRAINT DELIVERY_FROMID
FOREIGN KEY (From_Id) REFERENCES LOCATION(LocationId)
ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT DELIVERY_TOID
FOREIGN KEY (To_Id) REFERENCES LOCATION(LocationId)
ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE STOCK
(LocationId INT NOT NULL,
Varietal VARCHAR(30) NOT NULL,
Vintage INT NOT NULL,
StockQty INT NOT NULL,
PRIMARY KEY (LocationId, Varietal, Vintage),
CONSTRAINT STOCK_LOCATIONID
FOREIGN KEY (LocationId) REFERENCES LOCATION(LocationId)
ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT STOCK_VARIETAL
FOREIGN KEY (Varietal) REFERENCES WINE(Varietal)
ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE MOVED
(DeliveryId INT NOT NULL,
Varietal VARCHAR(30) NOT NULL,
Vintage INT NOT NULL,
Quantity INT NOT NULL,
PRIMARY KEY (DeliveryId, Varietal, Vintage),
CONSTRAINT MOVED_TRANSACTIONID
FOREIGN KEY (DeliveryId) REFERENCES DELIVERY(DeliveryId)
ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT MOVED_VARIETAL
FOREIGN KEY (Varietal) REFERENCES WINE(Varietal)
ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE CREATES
(LocationId INT NOT NULL,
DeliveryId INT NOT NULL,
PRIMARY KEY (LocationId, DeliveryId),
CONSTRAINT CREATES_LOCATIONID
FOREIGN KEY (LocationId) REFERENCES LOCATION(LocationId)
ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT CREATES_TRANSACTIONID
FOREIGN KEY (DeliveryId) REFERENCES DELIVERY(DeliveryId)
ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE HOLD
(HoldId INT NOT NULL,
Varietal VARCHAR(20) NOT NULL,
Vintage INT,
Qty INT NOT NULL,
PRIMARY KEY (HoldId, Varietal, Vintage),
CONSTRAINT HOLD_VARIETAL
FOREIGN KEY (Varietal) REFERENCES WINE(Varietal)
ON DELETE CASCADE ON UPDATE CASCADE);

INSERT INTO WINE (Varietal, Vintage, Price) VALUES ('Merlot', 2007, 50.49);
INSERT INTO WINE (Varietal, Vintage, Price) VALUES ('Malbec', 2004, 30.69);
INSERT INTO WINE (Varietal, Vintage, Price) VALUES ('Pinot', 2007, 24.99);


After this, the database and tables are created and you are ready to test the program. 

To Test the Program we first configured the run configuration of our main method. For command line arguments add: 
Database URL
Username
Password

Below is example testing: 



Connected to the database
Please choose what you would like to do: 
    [0]  Create Tables
    [1]  Add an Item
    [2]  Edit
    [3]  List
    [4]  Search for an Item
    [5]  Delete an Item
    [6]  Exit

Enter your choice: 3
What table would you like Listed
    [1] Wine
    [2] Customer
    [3] Location
    [4] Transaction
    [5] Delivery
    [6] Stock
    [7] Moved
    [8] Creates
    [9] Holds
1
WINE
Malbec	2004	30.69
Merlot	2007	50.49
Pinot	2007	24.99

Please choose what you would like to do: 
    [0]  Create Tables
    [1]  Add an Item
    [2]  Edit
    [3]  List
    [4]  Search for an Item
    [5]  Delete an Item
    [6]  Exit

Enter your choice: 1
Select a table for inserting values:
    [0] Wine
    [1] Customer
    [2] Location
    [3] Transactions
    [4] Delivery
    [5] Stock
    [6] Moved
    [7] Creates
    [8] Hold
    [9] Cancel
0
Please enter a value to insert for Varietal:
Coral
Please enter a value to insert for Vintage:
2012
Please enter a value to insert for Price:
24.49
Insert Operation was Successful
Please choose what you would like to do: 
    [0]  Create Tables
    [1]  Add an Item
    [2]  Edit
    [3]  List
    [4]  Search for an Item
    [5]  Delete an Item
    [6]  Exit

Enter your choice: 3
What table would you like Listed
    [1] Wine
    [2] Customer
    [3] Location
    [4] Transaction
    [5] Delivery
    [6] Stock
    [7] Moved
    [8] Creates
    [9] Holds
1
WINE
Coral	2012	24.49
Malbec	2004	30.69
Merlot	2007	50.49
Pinot	2007	24.99

Please choose what you would like to do: 
    [0]  Create Tables
    [1]  Add an Item
    [2]  Edit
    [3]  List
    [4]  Search for an Item
    [5]  Delete an Item
    [6]  Exit

Enter your choice: 5
Enter the table name you wish to edit: 
List of available tables: WINE, CUSTOMER, LOCATION, TRANSACTIONS, DELIVERY, STOCK, MOVED, CREATES, HOLD.
WINE
Enter the condition for deletion (e.g., WHERE column_name = value): 
WHERE Vintage = 2012
1 rows deleted successfully.
Please choose what you would like to do: 
    [0]  Create Tables
    [1]  Add an Item
    [2]  Edit
    [3]  List
    [4]  Search for an Item
    [5]  Delete an Item
    [6]  Exit

Enter your choice: 3
What table would you like Listed
    [1] Wine
    [2] Customer
    [3] Location
    [4] Transaction
    [5] Delivery
    [6] Stock
    [7] Moved
    [8] Creates
    [9] Holds
1
WINE
Malbec	2004	30.69
Merlot	2007	50.49
Pinot	2007	24.99

Please choose what you would like to do: 
    [0]  Create Tables
    [1]  Add an Item
    [2]  Edit
    [3]  List
    [4]  Search for an Item
    [5]  Delete an Item
    [6]  Exit

Enter your choice: 2
What table would you like to Edit
    [1] Wine
    [2] Customer
    [3] Location
    [4] Transaction
    [5] Delivery
    [6] Stock
    [7] Moved
    [8] Creates
    [9] Holds
1
WINE
Malbec	2004	30.69
Merlot	2007	50.49
Pinot	2007	24.99
Please enter the Varietal and Vintage you would like to update
Varietal: 
Malbec
Vintage: 
2004
Please enter the new Price
30.00
Successfully updated Wine
Please choose what you would like to do: 
    [0]  Create Tables
    [1]  Add an Item
    [2]  Edit
    [3]  List
    [4]  Search for an Item
    [5]  Delete an Item
    [6]  Exit

Enter your choice: 3
What table would you like Listed
    [1] Wine
    [2] Customer
    [3] Location
    [4] Transaction
    [5] Delivery
    [6] Stock
    [7] Moved
    [8] Creates
    [9] Holds
1
WINE
Malbec	2004	30.0
Merlot	2007	50.49
Pinot	2007	24.99

Please choose what you would like to do: 
    [0]  Create Tables
    [1]  Add an Item
    [2]  Edit
    [3]  List
    [4]  Search for an Item
    [5]  Delete an Item
    [6]  Exit

Enter your choice: 4
Please enter the SELECT statement you want to search the database for. (Ex. 'SELECT * FROM WINE' will return all of the values in the WINE Table)
List of available tables: WINE, CUSTOMER, LOCATION, TRANSACTION, DELIVERY, STOCK, MOVED, CREATES, HOLD.
SELECT * FROM WINE
Varietal	Vintage	Price	
Malbec	2004	30.0	
Merlot	2007	50.49	
Pinot	2007	24.99	
Please choose what you would like to do: 
    [0]  Create Tables
    [1]  Add an Item
    [2]  Edit
    [3]  List
    [4]  Search for an Item
    [5]  Delete an Item
    [6]  Exit

Enter your choice: 6

Process finished with exit code 0



