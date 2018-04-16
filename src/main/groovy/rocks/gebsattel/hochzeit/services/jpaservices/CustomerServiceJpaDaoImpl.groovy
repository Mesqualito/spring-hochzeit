package rocks.gebsattel.hochzeit.services.jpaservices

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import rocks.gebsattel.hochzeit.domain.Customer
import rocks.gebsattel.hochzeit.services.CustomerService
import rocks.gebsattel.hochzeit.services.security.EncryptionService

import javax.persistence.EntityManager

@Service
@Profile("jpadao")
class CustomerServiceJpaDaoImpl extends AbstractJpaDaoService implements CustomerService {

    private EncryptionService encryptionService

    @Autowired
    void setEncryptionService(EncryptionService encryptionService){
        this.encryptionService = encryptionService
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
}
