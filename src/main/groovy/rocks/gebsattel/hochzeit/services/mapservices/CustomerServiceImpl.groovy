package rocks.gebsattel.hochzeit.services.mapservices

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import rocks.gebsattel.hochzeit.commands.CustomerForm
import rocks.gebsattel.hochzeit.converters.CustomerFormToCustomer
import rocks.gebsattel.hochzeit.domain.Customer
import rocks.gebsattel.hochzeit.domain.DomainObject
import rocks.gebsattel.hochzeit.services.CustomerService

@Service
@Profile("map")
class CustomerServiceImpl extends AbstractMapService implements CustomerService {

    CustomerFormToCustomer customerFormToCustomer

    @Autowired
    void setCustomerFormToCustomer(CustomerFormToCustomer customerFormToCustomer){
        this.customerFormToCustomer = customerFormToCustomer
    }

    @Override
    List<DomainObject> listAll(){ super.listAll() }

    @Override
    Customer getById(Integer id){ (Customer) super.getById(id) }

    @Override
    Customer saveOrUpdate(Customer domainObject) { (Customer) super.saveOrUpdate(domainObject) }

    @Override
    void delete(Integer id){ super.delete(id) }

    @Override
    Customer saveOrUpdateCustomerForm(CustomerForm customerForm){
        Customer newCustomer = customerFormToCustomer.convert(customerForm)

        if(newCustomer.getUser().getId() != null) {
            Customer existingCustomer = getById(newCustomer.getId())
            newCustomer.getUser().setEnabled(existingCustomer.getUser().getEnabled())
        }
        return saveOrUpdate(newCustomer)

    }

}
