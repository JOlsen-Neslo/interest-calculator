package za.co.interview.interestcalculator.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "TBL_AGREEMENTS")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "Agreement.agreement_interest_calculation",
                procedureName = "agreement_interest_calculation",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "amount", type = BigDecimal.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "agreementType", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "startDate", type = Date.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "endDate", type = Date.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "repoRate", type = BigDecimal.class),

                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "responseCode", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "reason", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "calculatedInterest", type = BigDecimal.class)
                })
})
public class Agreement {

    @GeneratedValue
    @Id
    private Long id;

    @Column(name = "AGREEMENT_TYPE")
    private String agreementType;

    @Column(name = "FORMULA")
    private String formula;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAgreementType() {
        return agreementType;
    }

    public void setAgreementType(String agreementType) {
        this.agreementType = agreementType;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

}
