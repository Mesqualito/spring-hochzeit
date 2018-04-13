package rocks.gebsattel.hochzeit.services

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import rocks.gebsattel.hochzeit.domain.Customer

import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.PersistenceUnit

@Service
@Profile("jpadao")
class CustomerServiceJpaDaoImpl implements CustomerService {

    private EntityManagerFactory emf

    @PersistenceUnit
    void setEmf(EntityManagerFactory emf) {
        this.emf = emf
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
