DELIMITER //

CREATE PROCEDURE TransferFunds(IN source_account_id INT, IN destination_account_id INT, IN amount DECIMAL(10,2))
BEGIN
    DECLARE current_balance DECIMAL(10,2);

    -- Check the balance of the source account
    SELECT balance INTO current_balance FROM Accounts WHERE account_id = source_account_id;

    -- Check if the source account has sufficient balance
    IF current_balance >= amount THEN
        -- Deduct the amount from the source account
        UPDATE Accounts SET balance = balance - amount WHERE account_id = source_account_id;

        -- Add the amount to the destination account
        UPDATE Accounts SET balance = balance + amount WHERE account_id = destination_account_id;
    ELSE
        -- Insufficient funds, raise an error
        SIGNAL SQLSTATE '5000' SET MESSAGE_TEXT = 'Insufficient funds in the source account';
    END IF;
    
END //

DELIMITER ;
