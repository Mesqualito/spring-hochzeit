package rocks.gebsattel.hochzeit.services.jpaservices

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import rocks.gebsattel.hochzeit.commands.CustomerForm
import rocks.gebsattel.hochzeit.converters.CustomerFormToCustomer
import rocks.gebsattel.hochzeit.domain.Customer
import rocks.gebsattel.hochzeit.services.CustomerService
import rocks.gebsattel.hochzeit.services.security.EncryptionService

import javax.persistence.EntityManager

@Service
@Profile("jpadao")
class CustomerServiceJpaDaoImpl extends AbstractJpaDaoService implements CustomerService {

    private EncryptionService encryptionService
    private CustomerFormToCustomer customerFormToCustomer

    @Autowired
    void setEncryptionService(EncryptionService encryptionService){
        this.encryptionService = encryptionService
    }

    @Autowired
    void setCustomerFormToCustomer(CustomerFormToCustomer customerFormToCustomer){
        this.customerFormToCustomer = customerFormToCustomer
    }

    @Override
    List<Customer> listAll() {
        EntityManager em = emf.createEntityManager()
        em.createQuery("FROM Customer", Customer.class).getResultList()
    }

    @Override
    Customer getById(Integer id) {
        EntityManager em = emf.createEntityManager()
        em.find(Customer.class, id)
    }

    @Override
    Customer saveOrUpdate(Customer domainObject) {
        EntityManager em = emf.createEntityManager()
        em.getTransaction().begin()

        if (domainObject.getUser() != null && domainObject.getUser().getPassword() != null ) {
            domainObject.getUser().setEncryptedPassword(encryptionService.encryptString(domainObject.getUser().getPassword()))
        }

        Customer savedCustomer = em.merge(domainObject)
        em.getTransaction().commit()
        return savedCustomer

    }

    @Override
    void delete(Integer id) {
        EntityManager em = emf.createEntityManager()
        em.getTransaction().begin()
        em.remove(em.find(Customer.class, id))
        em.getTransaction().commit()
    }

    @Override
    Customer saveOrUpdateCustomerForm(CustomerForm customerForm){
        Customer newCustomer = customerFormToCustomer.convert(customerForm)

        // enhance if saved
        if(newCustomer.getUser().getId() != null){
            Customer existingCustomer = getById(newCustomer.getUser().getId())

            // set enabled-Flag from db
            newCustomer.getUser().setEnabled(existingCustomer.getUser().getEnabled())
        }

        return saveOrUpdate(newCustomer)
    }
}
