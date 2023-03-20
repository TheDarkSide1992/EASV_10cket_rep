USE master;
GO

DROP DATABASE IF EXISTS EASV_10cket_X;
GO

CREATE DATABASE EASV_10cket_X;
GO

USE EASV_10cket_X;
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

    CONSTRAINT PK_USER_ID PRIMARY KEY(User_ID),

    CONSTRAINT FK_USER_TYPE FOREIGN KEY(User_Type)
    REFERENCES User_Type(User_Type_ID)
)
GO

CREATE TABLE User_Paswords_(
    User_User_ID                INT                                     NOT NULL,
    Users_Pasword_              Binary(500)                             NOT NULL,

    CONSTRAINT FK_USER_USER_ID FOREIGN KEY(User_User_ID)
    REFERENCES User_(User_ID)
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

CREATE TABLE Event_(
    Event_ID                    INT IDENTITY(1,1)                       NOT NULL,
    Event_TIttle                NVARCHAR(250)                           NOT NULL,
    Event_Location              NVARCHAR(250)                           NOT NULL,
    Event_Event_Cordinator_ID   INT                                     NOT NULL,
    Evnet_Authors               NVARCHAR(250)                           NOT NULL,
    Event_Date                  DATE                                    NOT NULL,
    Event_Start_Time            TIME                                    NOT NULL,
    Event_Description           NVARCHAR(750),
    Event_Ticket_Total          INT                                     NOT NULL,
    Event_Tickett_Sold          INT                                     NOT NULL,
    Event_Is_Actrtive           BIT                                     NOT NULL,
    Event_Img_                  VARBINARY(MAX),

    CONSTRAINT PK_EVENT_ID PRIMARY KEY(Event_ID),

    CONSTRAINT FK_EVENT_EVENT_CORDINATOR_ID FOREIGN KEY(Event_Event_Cordinator_ID)
    REFERENCES User_(User_ID)
)
GO

CREATE TABLE Ticket_(
    Ticket_Content_ID           INT IDENTITY(1,1)                       NOT NULL,
    Ticket_Event_ID             INT                                     NOT NULL,
    Ticket_Contains             NVARCHAR(100)                           NOT NULL,

    CONSTRAINT PK_TICKET_CONTENT_ID PRIMARY KEY(Ticket_Content_ID),

    CONSTRAINT FK_TICKET_EVENT_ID FOREIGN KEY(Ticket_Event_ID)
    REFERENCES Event_(Event_ID)
)
GO

CREATE TABLE Ticket_Customer_Realation(
    TCR_Customer_ID             INT                                     NOT NULL,
    TCR_Ticket_ID               INT                                     NOT NULL,

    CONSTRAINT FK_TCR_CUSTOMER_ID FOREIGN KEY(TCR_Customer_ID)
    REFERENCES Customer(Customer_ID),

    CONSTRAINT FK_TCR_TICKET_ID FOREIGN KEY(TCR_Ticket_ID)
    REFERENCES Ticket_(Ticket_Content_ID)
)
GO

