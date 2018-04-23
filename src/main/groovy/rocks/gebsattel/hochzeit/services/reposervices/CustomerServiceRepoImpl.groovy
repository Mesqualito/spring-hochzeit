package rocks.gebsattel.hochzeit.services.reposervices

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import rocks.gebsattel.hochzeit.domain.Customer
import rocks.gebsattel.hochzeit.repositories.CustomerRepository
import rocks.gebsattel.hochzeit.services.CustomerService

@Service
@Profile("springdatajpa")
class CustomerServiceRepoImpl implements CustomerService {

    CustomerRepository customerRepository

    @Autowired
    void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository
    }

    @Override
    List<?> listAll(){
        List<Customer> customers = new ArrayList<>()
        // Java 8, method reference: customerRepository.findAll().forEach(customers::add)
        customerRepository.findAll().each{ customers.add(it) }
        return customers
    }

    @Override
    Customer getById(Integer id) { customerRepository.findById(id) }

    @Override
    Customer saveOrUpdate(Customer domainObject) { customerRepository.save(domainObject) }

    @Override
    void delete(Integer id) {
        customerRepository.delete(id)
    }
}
