package rocks.gebsattel.hochzeit.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import rocks.gebsattel.hochzeit.domain.Customer
import rocks.gebsattel.hochzeit.services.CustomerService

@RequestMapping("/customer")
@Controller
class CustomerController {

    private CustomerService customerService

    @Autowired
    void setCustomerService(CustomerService customerService){
        this.customerService = customerService
    }

    @RequestMapping( ["/list", "/"] )
    String listCustomers(Model model){
        model.addAttribute("customers", customerService.listAll())
        return "customer/list"
    }

    @RequestMapping("/show/{id}")
    String showCustomer(@PathVariable Integer id, Model model){
        model.addAttribute("customer", customerService.getById(id))
        return "customer/show"
    }

    @RequestMapping("/edit/{id}")
    String edit(@PathVariable Integer id, Model model){
        model.addAttribute("customer", customerService.getById(id))
        return "customer/customerform"
    }

    @RequestMapping("/new")
    String newCustomer(Model model){
        model.addAttribute("customer", new Customer())
        return "customer/customerform"
    }

    @RequestMapping(method = RequestMethod.POST)
    String saveOrUpdate(Customer customer){
        Customer newCustomer  = customerService.saveOrUpdate(customer)
        return "redirect:/customer/show/" + newCustomer.getId()
    }

    @RequestMapping("/delete/{id}")
    String delete(@PathVariable Integer id){
        customerService.delete(id)
        return "redirect:/customer/list"
    }
}
