CREATE OR REPLACE PROCEDURE UpdateSalary (
    employee_id IN NUMBER,
    percentage IN NUMBER
)
IS
    employee_not_found EXCEPTION;
    v_count NUMBER;
    
BEGIN
    -- Check if the employee exists
    SELECT COUNT(*) INTO v_count FROM Employees WHERE id = employee_id FOR UPDATE;
    
    IF v_count = 0 THEN
        RAISE employee_not_found;
    ELSE
        -- Update the employee's salary
        UPDATE Employees 
        SET salary = salary + (salary * (percentage / 100)) 
        WHERE id = employee_id;
    END IF;
    COMMIT;

EXCEPTION
    WHEN employee_not_found THEN
        -- Log the error
        INSERT INTO ErrorLogs (message, log_time) VALUES ('Employee ID not found', SYSDATE);
        ROLLBACK;
    WHEN OTHERS THEN
        -- Log any other errors
        INSERT INTO ErrorLogs (message, log_time) VALUES (SQLERRM, SYSDATE);
        ROLLBACK;


END UpdateSalary;
