CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
    current_balance NUMBER;
BEGIN
    -- Check if the transaction is a withdrawal
    IF :NEW.transaction_type = 'WITHDRAWAL' THEN
        -- Retrieve the current balance of the account
        SELECT balance INTO current_balance FROM Accounts WHERE account_id = :NEW.account_id FOR UPDATE;

        -- Ensure the withdrawal amount does not exceed the current balance
        IF :NEW.amount > current_balance THEN
            RAISE_APPLICATION_ERROR(-20001, 'Withdrawal amount exceeds current balance.');
        END IF;
    ELSIF :NEW.transaction_type = 'DEPOSIT' THEN
        -- Ensure the deposit amount is positive
        IF :NEW.amount <= 0 THEN
            RAISE_APPLICATION_ERROR(-20002, 'Deposit amount must be positive.');
        END IF;
    END IF;
END CheckTransactionRules;
/
