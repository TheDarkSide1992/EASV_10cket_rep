INSERT INTO User_ VALUES('Def_EV_Cord', 'DEFAULT', (SELECT DISTINCT User_Type_ID FROM User_Type WHERE USER_TYPE_TYPE = 'Event Coordinator'), 'N/A@EASV.dk', 'TLF = N/A', null)
INSERT INTO User_ VALUES('Def_EV_Adm', 'DEFAULT', (SELECT DISTINCT User_Type_ID FROM User_Type WHERE USER_TYPE_TYPE = 'Administrator'), 'N/A@EASV.dk', 'TLF = N/A', null)
GO


-- 10Cket123456
-- $2a$16$SQKnctQxB89dhg7qDpjUpOK2.04cD2hcW8rdNVQ7isDBrUsXYrkTu

-- Salt
-- $2a$16$SQKnctQxB89dhg7qDpjUpO

INSERT INTO User_Passwords VALUES((SELECT DISTINCT User_ID FROM User_  WHERE User_Name = 'Def_EV_Cord'),'Def_EV_Cord', '$2a$16$SQKnctQxB89dhg7qDpjUpOK2.04cD2hcW8rdNVQ7isDBrUsXYrkTu', '$2a$16$SQKnctQxB89dhg7qDpjUpO')
INSERT INTO User_Passwords VALUES((SELECT DISTINCT User_ID FROM User_  WHERE User_Name = 'Def_EV_Adm'), 'Def_EV_Adm', '$2a$16$SQKnctQxB89dhg7qDpjUpOK2.04cD2hcW8rdNVQ7isDBrUsXYrkTu', '$2a$16$SQKnctQxB89dhg7qDpjUpO')
GO


INSERT INTO Event_(Event_Title, Event_Location, Event_Event_Coordinator_ID, Event_Authors, Event_Date, Event_Start_Time, Event_Description, Event_Ticket_Total, Event_Ticket_Sold, Event_Is_Active, Event_Img)
VALUES('Open Mic Night', 'THE BASEMENT', 1, 'N/A',  convert(date, '2023-5-15'), convert(time, '18:00:00'), 'N/A', 100, 0, 1, CAST('N/A' AS VARBINARY(MAX)))

INSERT INTO Event_(Event_Title, Event_Location, Event_Event_Coordinator_ID, Event_Authors, Event_Date, Event_Start_Time, Event_Description, Event_Ticket_Total, Event_Ticket_Sold, Event_Is_Active, Event_Img)
VALUES('Game Night', 'THE BASEMENT', 1, 'N/A',  convert(date, '2023-4-05'), convert(time, '17:30:00'), 'N/A', 100, 0, 1, CAST('N/A' AS VARBINARY(MAX)))

INSERT INTO Event_(Event_Title, Event_Location, Event_Event_Coordinator_ID, Event_Authors, Event_Date, Event_Start_Time, Event_Description, Event_Ticket_Total, Event_Ticket_Sold, Event_Is_Active, Event_Img)
VALUES('Singer/Songwriter Jesus', 'THE BASEMENT', 1, 'N/A',  convert(date, '2023-7-25'), convert(time, '19:00:00'), 'N/A', 100, 0, 1, CAST('N/A' AS VARBINARY(MAX)))

INSERT INTO Event_(Event_Title, Event_Location, Event_Event_Coordinator_ID, Event_Authors, Event_Date, Event_Start_Time, Event_Description, Event_Ticket_Total, Event_Ticket_Sold, Event_Is_Active, Event_Img)
VALUES('INTRO PARTY', 'THE BASEMENT', 1, 'N/A',  convert(date, '2023-9-18'), convert(time, '17:45:00'), 'N/A', 100, 0, 1, CAST('N/A' AS VARBINARY(MAX)))
GO

SELECT * FROM User_Type;
GO

SELECT * FROM User_ WHERE User_Type = (SELECT User_Type_ID FROM User_Type WHERE USER_TYPE_TYPE = 'Event Coordinator')
GO

SELECT * FROM Event_
GO

SELECT * FROM User_Passwords
GO

SELECT * FROM User_ WHERE User_ID = (SELECT User_User_ID FROM User_Passwords WHERE (User_User_Name = 'Def_EV_Cord') AND (Users_Password = '$2a$16$SQKnctQxB89dhg7qDpjUpOK2.04cD2hcW8rdNVQ7isDBrUsXYrkTu'))
SELECT * FROM User_ WHERE User_ID = (SELECT User_User_ID FROM User_Passwords WHERE (User_User_Name = 'Def_EV_Adm') AND (Users_Password = '$2a$16$SQKnctQxB89dhg7qDpjUpOK2.04cD2hcW8rdNVQ7isDBrUsXYrkTu'))
GO