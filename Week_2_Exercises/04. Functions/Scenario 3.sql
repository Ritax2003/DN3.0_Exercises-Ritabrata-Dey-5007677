CREATE OR REPLACE FUNCTION HasSufficientBalance (
    account_id IN NUMBER,
    amount IN NUMBER
) RETURN BOOLEAN
IS
    current_balance NUMBER;
BEGIN
    -- Retrieve the current balance of the account
    SELECT balance INTO current_balance FROM Accounts WHERE account_id = account_id;
    
    -- Check if the balance is sufficient
    IF current_balance >= amount THEN
        RETURN TRUE;
    ELSE
        RETURN FALSE;
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        -- Handle the case where the account ID does not exist
        RETURN FALSE;
    WHEN OTHERS THEN
        -- Handle any other exceptions
        RETURN FALSE;
        
END HasSufficientBalance;

