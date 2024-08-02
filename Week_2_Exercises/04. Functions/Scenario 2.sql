CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment (
    loan_amount IN NUMBER,
    annual_interest_rate IN NUMBER,
    loan_duration_years IN NUMBER
) RETURN NUMBER
IS
    monthly_interest_rate NUMBER;
    number_of_payments NUMBER;
    monthly_installment NUMBER;
BEGIN
    monthly_interest_rate := annual_interest_rate / 12 / 100;
    number_of_payments := loan_duration_years * 12;
    
    IF monthly_interest_rate = 0 THEN
        monthly_installment := loan_amount / number_of_payments;
    ELSE
        monthly_installment := loan_amount * monthly_interest_rate / 
            (1 - POWER(1 + monthly_interest_rate, -number_of_payments));
    END IF;
    
    RETURN monthly_installment;
    
END CalculateMonthlyInstallment;

