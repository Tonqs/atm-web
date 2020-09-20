package th.ac.ku.ATM.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String getCustomerPage(Model model){

        model.addAttribute("allCustomer", customerService);
        return "customer"; // customer.html
    }

    @PostMapping
    public String registerCustomer(@ModelAttribute Customer customer, Model model){
        customerService.createCustomer(customer);
        model.addAttribute("allCustomer", customerService);
        return "redirect:customer";
    }

}
