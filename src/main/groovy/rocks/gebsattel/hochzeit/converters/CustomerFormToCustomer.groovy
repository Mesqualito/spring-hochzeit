package rocks.gebsattel.hochzeit.converters

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import rocks.gebsattel.hochzeit.commands.CustomerForm
import rocks.gebsattel.hochzeit.domain.Address
import rocks.gebsattel.hochzeit.domain.Customer
import rocks.gebsattel.hochzeit.domain.User

// springframework-Converter expects two Generics
// to convert from arg[0] to arg[1]
@Component
class CustomerFormToCustomer implements Converter<CustomerForm, Customer> {

    @Override
    Customer convert(CustomerForm customerForm) {

        Customer customer = new Customer()
        customer.setUser(new User())
        customer.setBillingAddress(new Address())
        customer.setShippingAddress(new Address())
        customer.getUser().setId(customerForm.getUserId())
        customer.getUser().setVersion(customerForm.getUserVersion())
        customer.setId(customerForm.getCustomerId())
        customer.setVersion(customerForm.getCustomerVersion())
        customer.getUser().setUsername(customerForm.getUserName())
        customer.getUser().setPassword(customerForm.getPasswordText())
        customer.setFirstName(customerForm.getFirstName())
        customer.setLastName(customerForm.getLastName())
        customer.seteMail(customerForm.geteMail())
        customer.setPhoneNr(customerForm.getPhoneNr())

        return customer
    }
}
