package za.co.interview.interestcalculator.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import za.co.interview.interestcalculator.models.Agreement;
import za.co.interview.interestcalculator.repositories.IAgreementRepository;

@Component
public class DatabaseSeeder {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseSeeder.class);

    private final IAgreementRepository agreementRepository;

    @Autowired
    public DatabaseSeeder(IAgreementRepository agreementRepository) {
        this.agreementRepository = agreementRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        logger.info("Starting to seed..");
        agreementSeed();
        logger.info("Completed seeding.");
    }

    private void agreementSeed() {
        Agreement mortgageAgreement = new Agreement();
        mortgageAgreement.setAgreementType("Mortgage Agreements");
        mortgageAgreement.setFormula("(RR * 2.2) + 5/100");
        agreementRepository.save(mortgageAgreement);

        Agreement creditFacilities = new Agreement();
        creditFacilities.setAgreementType("Credit Facilities");
        creditFacilities.setFormula("(RR * 2.2) + 10/100");
        agreementRepository.save(creditFacilities);

        Agreement unsecured = new Agreement();
        unsecured.setAgreementType("Unsecured Credit Transactions");
        unsecured.setFormula("(RR * 2.2) + 20/100");
        agreementRepository.save(unsecured);

        Agreement smallBusiness = new Agreement();
        smallBusiness.setAgreementType("Developmental credit agreements For development of a small business");
        smallBusiness.setFormula("(RR * 2.2) + 20/100");
        agreementRepository.save(smallBusiness);

        Agreement lowIncome = new Agreement();
        lowIncome.setAgreementType("Developmental credit agreements For low income housing (unsecured)");
        lowIncome.setFormula("(RR * 2.2) + 20/100");
        agreementRepository.save(lowIncome);

        Agreement shortTerm = new Agreement();
        shortTerm.setAgreementType("Short term credit transactions");
        shortTerm.setFormula("5*12"); // convert to p.a
        agreementRepository.save(shortTerm);

        Agreement otherCredit = new Agreement();
        otherCredit.setAgreementType("Other credit agreements");
        otherCredit.setFormula("(RR * 2.2) + 10/100");
        agreementRepository.save(otherCredit);

        Agreement incidental = new Agreement();
        incidental.setAgreementType("Incidental credit agreements");
        incidental.setFormula("2*12"); // convert to p.a
        agreementRepository.save(incidental);
    }


}
