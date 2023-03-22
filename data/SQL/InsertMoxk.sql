INSERT INTO User_Type VALUES ('Administrator');
INSERT INTO User_Type VALUES ('Event Kordinator');
GO

INSERT INTO User_ VALUES('Def_EV_Cord', 'DEFAULT', (SELECT DISTINCT User_Type_ID FROM User_Type WHERE USER_TYPE_TYPE = 'Event Kordinator'), 'N/A@EASV.dk', 'TLF = N/A')
GO

INSERT INTO Event_(Event_TIttle, Event_Location, Event_Event_Cordinator_ID, Evnet_Authors, Event_Date, Event_Start_Time, Event_Description, Event_Ticket_Total, Event_Tickett_Sold, Event_Is_Actrtive, Event_Img_)
VALUES('Open Mic Night', 'THE BASEMENT', 1, 'N/A',  convert(date, '2023-5-15'), convert(time, '18:00:00'), 'N/A', 100, 0, 1, CAST('N/A' AS VARBINARY(MAX)))

INSERT INTO Event_(Event_TIttle, Event_Location, Event_Event_Cordinator_ID, Evnet_Authors, Event_Date, Event_Start_Time, Event_Description, Event_Ticket_Total, Event_Tickett_Sold, Event_Is_Actrtive, Event_Img_)
VALUES('Game Night', 'THE BASEMENT', 1, 'N/A',  convert(date, '2023-4-05'), convert(time, '17:30:00'), 'N/A', 100, 0, 1, CAST('N/A' AS VARBINARY(MAX)))

INSERT INTO Event_(Event_TIttle, Event_Location, Event_Event_Cordinator_ID, Evnet_Authors, Event_Date, Event_Start_Time, Event_Description, Event_Ticket_Total, Event_Tickett_Sold, Event_Is_Actrtive, Event_Img_)
VALUES('Singer Song writter Jessus', 'THE BASEMENT', 1, 'N/A',  convert(date, '2023-7-25'), convert(time, '19:00:00'), 'N/A', 100, 0, 1, CAST('N/A' AS VARBINARY(MAX)))

INSERT INTO Event_(Event_TIttle, Event_Location, Event_Event_Cordinator_ID, Evnet_Authors, Event_Date, Event_Start_Time, Event_Description, Event_Ticket_Total, Event_Tickett_Sold, Event_Is_Actrtive, Event_Img_)
VALUES('INTRO PARTY', 'THE BASEMENT', 1, 'N/A',  convert(date, '2023-9-18'), convert(time, '17:45:00'), 'N/A', 100, 0, 1, CAST('N/A' AS VARBINARY(MAX)))
GO

SELECT * FROM User_Type;
GO

SELECT * FROM User_ WHERE User_Type = (SELECT User_Type_ID FROM User_Type WHERE USER_TYPE_TYPE = 'Event Kordinator')
GO

SELECT * FROM Event_
GO