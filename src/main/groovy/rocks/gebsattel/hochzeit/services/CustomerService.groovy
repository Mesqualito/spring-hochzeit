package rocks.gebsattel.hochzeit.services

import rocks.gebsattel.hochzeit.commands.CustomerForm
import rocks.gebsattel.hochzeit.domain.Customer

interface CustomerService extends CRUDService<Customer> {

    // handling CustomerForm-Object in the Service-Layer (not the controllers!)
    // with extending the CustomerService to except the CustomerForm-Object
    // and delegating it down to the services
    Customer saveOrUpdateCustomerForm(CustomerForm customerForm)

}