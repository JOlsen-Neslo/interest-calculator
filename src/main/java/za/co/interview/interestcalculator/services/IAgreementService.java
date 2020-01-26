package za.co.interview.interestcalculator.services;

import za.co.interview.interestcalculator.dtos.InterestCriteria;
import za.co.interview.interestcalculator.dtos.InterestResponse;

import java.util.List;

public interface IAgreementService {

    List<String> retrieveAllTypes();

    InterestResponse calculateInterest(InterestCriteria criteria);

}
