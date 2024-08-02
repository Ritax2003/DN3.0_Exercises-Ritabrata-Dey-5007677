-- Active: 1722572272774@@127.0.0.1@1521@XE@JAVAFSE
SET SERVEROUTPUT ON;
DECLARE
    CURSOR c_transactions IS
        SELECT DISTINCT c.CustomerID, c.Name, t.TransactionDate, t.Amount, t.TransactionType
        FROM Customers c
        JOIN Accounts a ON c.CustomerID = a.CustomerID
        JOIN Transactions t ON a.AccountID = t.AccountID
        WHERE EXTRACT(MONTH FROM t.TransactionDate) = EXTRACT(MONTH FROM SYSDATE)
          AND EXTRACT(YEAR FROM t.TransactionDate) = EXTRACT(YEAR FROM SYSDATE);
          
    v_customerID Customers.CustomerID%TYPE;
    v_name Customers.Name%TYPE;
    v_transactionDate Transactions.TransactionDate%TYPE;
    v_amount Transactions.Amount%TYPE;
    v_transactionType Transactions.TransactionType%TYPE;

BEGIN
    OPEN c_transactions;
    
    LOOP
        FETCH c_transactions INTO v_customerID, v_name, v_transactionDate, v_amount, v_transactionType;
        EXIT WHEN c_transactions%NOTFOUND;
        
        -- Print the statement (for demonstration purposes, using DBMS_OUTPUT)
        DBMS_OUTPUT.PUT_LINE('CustomerID: ' || v_customerID);
        DBMS_OUTPUT.PUT_LINE('Name: ' || v_name);
        DBMS_OUTPUT.PUT_LINE('Transaction Date: ' || v_transactionDate);
        DBMS_OUTPUT.PUT_LINE('Amount: ' || v_amount);
        DBMS_OUTPUT.PUT_LINE('Transaction Type: ' || v_transactionType);
        DBMS_OUTPUT.PUT_LINE('--------------------------');
    END LOOP;
    
    CLOSE c_transactions;
END;
/
