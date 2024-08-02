DELIMITER //

CREATE PROCEDURE UpdateEmployeeBonus(IN department_name VARCHAR(100), IN bonus_percentage DECIMAL(5,2))
BEGIN
    -- Update the salary by adding the bonus percentage
    UPDATE Employees
    SET salary = salary + (salary * bonus_percentage / 100)
    WHERE department = department_name;
    
END //

DELIMITER ;
