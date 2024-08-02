-- Active: 1722572272774@@127.0.0.1@1521@XE@JAVAFSE
CREATE OR REPLACE FUNCTION CalculateAge (
    date_of_birth IN DATE
) RETURN NUMBER

IS
    age NUMBER;

BEGIN
    age := FLOOR(MONTHS_BETWEEN(SYSDATE, date_of_birth) / 12);
    RETURN age;
    
END CalculateAge;
/