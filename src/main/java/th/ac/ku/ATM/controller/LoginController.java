package th.ac.ku.ATM.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import th.ac.ku.ATM.model.Customer;
import th.ac.ku.ATM.service.BankAccountService;
import th.ac.ku.ATM.service.CustomerService;

@Controller
@RequestMapping("/login")
public class LoginController {

    private CustomerService customerService;
    private BankAccountService bankAccountService;

    public LoginController(CustomerService customerService, BankAccountService bankAccountService) {
        this.customerService = customerService;
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public String getLoginPage(){
        return "login";
    }

    @PostMapping
    public String login(@ModelAttribute Customer customer, Model model){
        //1. check to see if id and pin matched
        Customer matchingCustomer = customerService.checkPin(customer);

        //2. if match , welcome
        if(matchingCustomer != null){
            model.addAttribute("customertitle",
                    matchingCustomer.getName() + " Bank Accounts");
            model.addAttribute("bankaccounts",
                    bankAccountService.getCustomerBankAccount(customer.getId()));
            return "customeraccount";

        }else {
            model.addAttribute("greeting", "Can't find customer");
            return "home";
        }
        //3. not match, customer info is incorrection
    }
}
