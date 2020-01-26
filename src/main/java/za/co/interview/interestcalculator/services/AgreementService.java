package za.co.interview.interestcalculator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.interview.interestcalculator.dtos.InterestCriteria;
import za.co.interview.interestcalculator.dtos.InterestResponse;
import za.co.interview.interestcalculator.repositories.IAgreementRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AgreementService implements IAgreementService {

    private IAgreementRepository agreementRepository;
    private static List<String> types;

    @Autowired
    public AgreementService(final IAgreementRepository agreementRepository) {
        this.agreementRepository = agreementRepository;
    }

    @Override
    public List<String> retrieveAllTypes() {
        // Only ever need to retrieve this list once
        if (types == null) {
            types = new ArrayList<>();
            agreementRepository.findAll().forEach(agreement -> types.add(agreement.getAgreementType()));
        }

        return types;
    }

    @Override
    public InterestResponse calculateInterest(InterestCriteria criteria) {
        Map<String, Object> response = this.agreementRepository.calculateInterest(
                criteria.getAmount(),
                criteria.getAgreementType(),
                criteria.getStartDate(),
                criteria.getEndDate(),
                criteria.getRepoRate()
        );

        return populateInterestResponse(response);
    }

    private InterestResponse populateInterestResponse(Map<String, Object> values) {
        InterestResponse interestResponse = new InterestResponse();

        Object responseCode = values.get("responseCode");
        if (responseCode != null) {
            interestResponse.setResponseCode((Integer) responseCode);
        }

        Object reason = values.get("reason");
        if (reason != null) {
            interestResponse.setReason((String) reason);
        }

        Object calculatedInterest = values.get("calculatedInterest");
        if (calculatedInterest != null) {
            interestResponse.setCalculatedInterest((BigDecimal) calculatedInterest);
        }

        return interestResponse;
    }

}
