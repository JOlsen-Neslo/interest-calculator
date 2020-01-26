package za.co.interview.interestcalculator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import za.co.interview.interestcalculator.dtos.InterestCriteria;
import za.co.interview.interestcalculator.dtos.InterestResponse;
import za.co.interview.interestcalculator.services.IAgreementService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AgreementController implements WebMvcConfigurer {

    private final IAgreementService agreementService;

    @Autowired
    public AgreementController(final IAgreementService agreementService) {
        this.agreementService = agreementService;
    }

    @PostMapping("/agreements/interest/calculate")
    public String calculateInterest(@Valid @ModelAttribute InterestCriteria interestCriteria, BindingResult result, Model model) {
        List<String> types = agreementService.retrieveAllTypes();
        model.addAttribute("types", types);

        if (result.hasErrors()) {
            model.addAttribute("interestCriteria", interestCriteria);
            return "interest";
        }

        InterestResponse interestResponse = this.agreementService.calculateInterest(interestCriteria);
        model.addAttribute("result", interestResponse.getResponseCode());
        model.addAttribute("reason", interestResponse.getReason());
        model.addAttribute("value", interestResponse.getCalculatedInterest());
        model.addAttribute("interestCriteria", new InterestCriteria());

        return "interest";
    }

    @GetMapping("/agreements/interest")
    public String interest(Model model) {
        List<String> types = agreementService.retrieveAllTypes();
        model.addAttribute("types", types);
        model.addAttribute("interestCriteria", new InterestCriteria());
        return "interest";
    }

}
