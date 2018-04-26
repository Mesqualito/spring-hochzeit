package rocks.gebsattel.hochzeit.converters

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import rocks.gebsattel.hochzeit.commands.CustomerForm
import rocks.gebsattel.hochzeit.domain.Customer

@Component
class CustomerToCustomerForm implements Converter<Customer, CustomerForm> {

    @Override
    CustomerForm convert(Customer customer) {

        CustomerForm customerForm = new CustomerForm()
        customerForm.setCustomerId(customer.getId())
        customerForm.setCustomerVersion(customer.getVersion())
        customerForm.setUserId(customer.getUser().getId())
        println customerForm.getUserId()
        customerForm.setUserVersion(customer.getUser().getVersion())
        customerForm.setPasswordText(customer.getUser().getEncryptedPassword())
        customerForm.setFirstName(customer.getFirstName())
        customerForm.setLastName(customer.getLastName())
        customerForm.seteMail((customer.geteMail()))
        customerForm.setPhoneNr(customer.getPhoneNr())

        return customerForm
    }
}
