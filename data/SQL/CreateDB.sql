USE master;
GO

DROP DATABASE IF EXISTS EASV_10cket_x;
GO

CREATE DATABASE EASV_10cket_x;
GO

USE EASV_10cket_x;
GO

CREATE TABLE User_Type(
    User_Type_ID                INT IDENTITY(1,1)                       NOT NULL,
    USER_TYPE_TYPE              NVARCHAR(60)                            NOT NULL,

    CONSTRAINT PK_USER_TYPE PRIMARY KEY(User_Type_ID)
)
GO

CREATE TABLE User_(
    User_ID                     INT IDENTITY(1,1)                       NOT NULL,
    User_Name                   NVARCHAR(250)                           NOT NULL,
    User_First_Name             NVARCHAR(250)                           NOT NULL,
    User_Type                   INT                                     NOT NULL,
    User_Email                  NVARCHAR(250)                           NOT NULL,
    User_tlf                    NVARCHAR(50)                            NOT NULL,
    User_Img                    VARBINARY(MAX),

    CONSTRAINT PK_USER_ID PRIMARY KEY(User_ID),

    CONSTRAINT FK_USER_TYPE FOREIGN KEY(User_Type)
    REFERENCES User_Type(User_Type_ID)
)
GO

CREATE TABLE Customer(
    Customer_ID                 INT IDENTITY(1,1)                       NOT NULL,
    Customer_First_Name         NVARCHAR(250)                           NOT NULL,
    Customer_Mail               NVARCHAR(250)                           NOT NULL,
    Customer_tlf                NVARCHAR(50)                            NOT NULL,
    Customer_Ticket_Bought      INT                                     NOT NULL,

    Customer_Monet_Spend        INT                                     NOT NULL,

    CONSTRAINT PK_CUSTOMER_ID PRIMARY KEY(Customer_ID)
)
GO

CREATE TABLE User_Passwords(
    User_User_ID                INT                                     NOT NULL,
    User_User_Name              NVARCHAR(250)                           NOT NULL,
    Users_Password              NVARCHAR(MAX)                           NOT NULL,
    Users_Salt                  NVARCHAR(MAX)                           NOT NULL,

    CONSTRAINT FK_USER_USER_ID FOREIGN KEY(User_User_ID)
    REFERENCES User_(User_ID),

)
GO

CREATE TABLE Event_(
    Event_ID                    INT IDENTITY(1,1)                       NOT NULL,
    Event_Title                 NVARCHAR(250)                           NOT NULL,
    Event_Location              NVARCHAR(250)                           NOT NULL,
    Event_LocationURL           NVARCHAR(250),
    Event_Event_Coordinator_ID  INT                                     NOT NULL,
    Event_Authors               NVARCHAR(250),
    Event_Date                  DATE                                    NOT NULL,
    Event_Start_Time            TIME                                    NOT NULL,
    Event_Description           NVARCHAR(750),
    Event_Ticket_Total          INT,
    Event_Ticket_Sold           INT,
    Event_Is_Active             BIT                                     NOT NULL,
    Event_Img                   VARBINARY(MAX),

    CONSTRAINT PK_EVENT_ID PRIMARY KEY(Event_ID),

    CONSTRAINT FK_EVENT_EVENT_COORDINATOR_ID FOREIGN KEY(Event_Event_Coordinator_ID)

    REFERENCES User_(User_ID)
)
GO

CREATE TABLE Ticket(
    Ticket_Content_ID           INT IDENTITY(1,1)                       NOT NULL,
    Ticket_Event_ID             INT                                     NOT NULL,
    Ticket_Contains             NVARCHAR(100)                           NOT NULL,
    Ticket_Price                INT                                     NOT NULL,
    Ticket_TotalNrOfTickets     INT,

    CONSTRAINT PK_TICKET_CONTENT_ID PRIMARY KEY(Ticket_Content_ID),

    CONSTRAINT FK_TICKET_EVENT_ID FOREIGN KEY(Ticket_Event_ID)
    REFERENCES Event_(Event_ID)
)
GO

CREATE TABLE Ticket_Customer_Relation(
    TCR_Customer_ID             INT                                     NOT NULL,
    TCR_Ticket_ID               INT                                     NOT NULL,

    CONSTRAINT FK_TCR_CUSTOMER_ID FOREIGN KEY(TCR_Customer_ID)
    REFERENCES Customer(Customer_ID),

    CONSTRAINT FK_TCR_TICKET_ID FOREIGN KEY(TCR_Ticket_ID)
    REFERENCES Ticket(Ticket_Content_ID)
)
GO

CREATE TABLE Submitted_For_Deletion(
    Submit_Delete_ID          INT IDENTITY(1,1)                       NOT NULL,
    Submit_Delete_Event       INT                                     NOT NULL,

    CONSTRAINT PK_SD_ID PRIMARY KEY(Submit_Delete_ID),

    CONSTRAINT FK_SD_Event_ID FOREIGN KEY(Submit_Delete_Event)
    REFERENCES Event_(Event_ID)
)
GO