DECLARE
    CURSOR c_loans IS
        SELECT LoanID, InterestRate
        FROM Loans;
    
    v_loanID Loans.LoanID%TYPE;
    v_currentInterestRate Loans.InterestRate%TYPE;
    v_newInterestRate NUMBER;
    
    -- Example function to calculate new interest rate
    FUNCTION calculate_new_interest_rate(p_current_rate NUMBER) RETURN NUMBER IS
    BEGIN
        RETURN p_current_rate + 0.01; -- Example: increase interest rate by 1%
    END;

BEGIN
    OPEN c_loans;
    
    LOOP
        FETCH c_loans INTO v_loanID, v_currentInterestRate;
        EXIT WHEN c_loans%NOTFOUND;
        
        v_newInterestRate := calculate_new_interest_rate(v_currentInterestRate);
        
        -- Update the interest rate
        UPDATE Loans
        SET InterestRate = v_newInterestRate
        WHERE LoanID = v_loanID;
        
        -- Optionally, log the update
        DBMS_OUTPUT.PUT_LINE('Updated LoanID: ' || v_loanID || ' to new interest rate: ' || v_newInterestRate);
    END LOOP;
    
    CLOSE c_loans;
END;
/
