SET SERVEROUTPUT ON;

DECLARE
    CURSOR c_loans IS
        SELECT l.LoanID, l.CustomerID, c.Name, l.EndDate
        FROM Loans l
        JOIN Customers c ON l.CustomerID = c.CustomerID
        WHERE l.EndDate BETWEEN SYSDATE AND  SYSDATE + 30;

BEGIN
    FOR rec IN c_loans LOOP
        DBMS_OUTPUT.PUT_LINE('Reminder: Dear ' || rec.Name || ', your loan (ID: ' || rec.LoanID || ') is due on ' || TO_CHAR(rec.EndDate, 'YYYY-MM-DD') || '.');
    END LOOP;
END;

