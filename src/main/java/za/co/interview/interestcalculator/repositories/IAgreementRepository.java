package za.co.interview.interestcalculator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.co.interview.interestcalculator.models.Agreement;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

@Repository
public interface IAgreementRepository extends JpaRepository<Agreement, Long> {

    @Procedure(name = "Agreement.agreement_interest_calculation")
    Map<String, Object> calculateInterest(
            @Param("amount") BigDecimal amount,
            @Param("agreementType") String agreementType,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            @Param("repoRate") BigDecimal repoRate
    );

}
