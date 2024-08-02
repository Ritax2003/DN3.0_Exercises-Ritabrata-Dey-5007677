CREATE OR REPLACE PROCEDURE SafeTransferFunds (
    from_account_id IN NUMBER,
    to_account_id IN NUMBER,
    amount IN NUMBER
)
IS
    insufficient_funds EXCEPTION;
    funds_balance NUMBER;
BEGIN
    -- Check balance of the from_account
    SELECT balance INTO funds_balance FROM Accounts WHERE account_id = from_account_id FOR UPDATE;
    
    IF funds_balance < amount THEN
        RAISE insufficient_funds;
    ELSE
        -- Deduct the amount from the from_account
        UPDATE Accounts SET balance = balance - amount WHERE account_id = from_account_id;
        
        -- Add the amount to the to_account
        UPDATE Accounts SET balance = balance + amount WHERE account_id = to_account_id;
    END IF;

    COMMIT;
EXCEPTION
    WHEN insufficient_funds THEN
        -- Log the error
        INSERT INTO ErrorLogs (message, log_time) VALUES ('Insufficient funds for transfer', SYSDATE);
        ROLLBACK;
    WHEN OTHERS THEN
        -- Log any other errors
        INSERT INTO ErrorLogs (message, log_time) VALUES (SQLERRM, SYSDATE);
        ROLLBACK;
END SafeTransferFunds;
