package za.co.interview.interestcalculator.dtos;

import java.math.BigDecimal;

public class InterestResponse {

    private int responseCode;
    private String reason;
    private BigDecimal calculatedInterest;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public BigDecimal getCalculatedInterest() {
        return calculatedInterest;
    }

    public void setCalculatedInterest(BigDecimal calculatedInterest) {
        this.calculatedInterest = calculatedInterest;
    }
}
