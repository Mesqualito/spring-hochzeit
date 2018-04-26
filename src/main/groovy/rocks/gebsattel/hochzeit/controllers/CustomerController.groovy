package rocks.gebsattel.hochzeit.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import rocks.gebsattel.hochzeit.commands.CustomerForm
import rocks.gebsattel.hochzeit.converters.CustomerFormToCustomer
import rocks.gebsattel.hochzeit.converters.CustomerToCustomerForm
import rocks.gebsattel.hochzeit.domain.Customer
import rocks.gebsattel.hochzeit.services.CustomerService

import javax.validation.Valid

@RequestMapping("/customer")
@Controller
class CustomerController {

    private CustomerService customerService
    private CustomerFormToCustomer customerFormToCustomer
    private CustomerToCustomerForm customerToCustomerForm

    @Autowired
    void setCustomerService(CustomerService customerService){ this.customerService = customerService }

    @Autowired
    void setCustomerFormToCustomer(CustomerFormToCustomer customerFormToCustomer){
        this.customerFormToCustomer = customerFormToCustomer
    }

    @Autowired
    void setCustomerToCustomerForm(CustomerToCustomerForm customerToCustomerForm){
        this.customerToCustomerForm = customerToCustomerForm
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
        model.addAttribute("customerForm", customerService.getById(id))
        return "customer/customerform"
    }

    @RequestMapping("/new")
    String newCustomer(Model model){
        model.addAttribute("customerForm", new CustomerForm())
        return "customer/customerform"
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    String saveOrUpdate(@Valid CustomerForm customerForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "customer/customerform"
        }

        Customer newCustomer  = customerService.saveOrUpdateCustomerForm(customerForm)
        return "redirect:/customer/show/" + newCustomer.getId()
    }

    @RequestMapping("/delete/{id}")
    String delete(@PathVariable Integer id){
        customerService.delete(id)
        return "redirect:/customer/list"
    }
}
