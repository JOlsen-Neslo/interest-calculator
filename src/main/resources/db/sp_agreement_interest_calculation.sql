DROP PROCEDURE IF EXISTS agreement_interest_calculation;

DELIMITER $$
CREATE PROCEDURE agreement_interest_calculation
    (
        IN amount DECIMAL(16, 2),
        IN agreementType VARCHAR(256),
        IN startDate DATE,
        IN endDate DATE,
        IN repoRate DECIMAL(16, 2),
        OUT responseCode INT,
        OUT reason VARCHAR(500),
        OUT calculatedInterest DECIMAL(16, 2)
    )
procedure_label:BEGIN
    IF amount IS NULL OR amount < 2
    THEN
        SET responseCode = 0;
        SET reason = 'Amount cannot be null or less than 1.';
        LEAVE procedure_label;
    ELSEIF agreementType IS NULL OR agreementType = ''
    THEN
        SET responseCode = 0;
        SET reason = 'Agreement type cannot be empty.';
        LEAVE procedure_label;
    ELSEIF startDate IS NULL
    THEN
        SET responseCode = 0;
        SET reason = 'Start Date cannot be null.';
        LEAVE procedure_label;
    ELSEIF endDate IS NULL
    THEN
        SET responseCode = 0;
        SET reason = 'Start Date cannot be null.';
        LEAVE procedure_label;
    ELSEIF endDate < startDate
    THEN
        SET responseCode = 0;
        SET reason = 'End Date cannot be before the start date.';
        LEAVE procedure_label;
    ELSEIF repoRate IS NULL
    THEN
        SET responseCode = 0;
        SET reason = 'Repo Rate cannot be null.';
        LEAVE procedure_label;
    END IF;

    SET @formula := (SELECT formula FROM tbl_agreements a WHERE UPPER(a.agreement_type) = UPPER(agreementType));
    SET @formula := REPLACE(@formula, 'RR', repoRate);
    SET @formula := CONCAT('SELECT ', @formula, '  INTO @percentage;');

    PREPARE formulaQuery FROM @formula;
    EXECUTE formulaQuery;

    SET calculatedInterest = (SELECT (amount * @percentage / 100) * (endDate - startDate) / 365);
    SET responseCode = 1;
END;

DELIMITER ;