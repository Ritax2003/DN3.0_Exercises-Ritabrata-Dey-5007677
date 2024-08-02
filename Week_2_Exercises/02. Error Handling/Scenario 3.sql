CREATE OR REPLACE PROCEDURE AddNewCustomer (
    customer_id IN NUMBER, 
    customer_name IN VARCHAR2, 
    customer_email IN VARCHAR2
)
IS
    customer_exists EXCEPTION;
    v_count NUMBER;
BEGIN
    -- Check if the customer already exists
    SELECT COUNT(*) INTO v_count FROM Customers WHERE id = customer_id;
    
    IF v_count > 0 THEN
        RAISE customer_exists;
    ELSE
        -- Insert the new customer
        INSERT INTO Customers (id, name, email) VALUES (customer_id, customer_name, customer_email);
    END IF;

    COMMIT;
EXCEPTION
    WHEN customer_exists THEN
        -- Log the error
        INSERT INTO ErrorLogs (message, log_time) VALUES ('Customer ID already exists', SYSDATE);
        ROLLBACK;
    WHEN OTHERS THEN
        -- Log any other errors
        INSERT INTO ErrorLogs (message, log_time) VALUES (SQLERRM, SYSDATE);
        ROLLBACK;
END AddNewCustomer;
