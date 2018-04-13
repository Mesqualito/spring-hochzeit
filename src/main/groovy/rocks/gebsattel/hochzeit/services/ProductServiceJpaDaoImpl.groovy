package rocks.gebsattel.hochzeit.services

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import rocks.gebsattel.hochzeit.domain.Product

import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.PersistenceUnit

@Service
@Profile("jpadao")
class ProductServiceJpaDaoImpl implements ProductService {

    private EntityManagerFactory emf

    @PersistenceUnit
    void setEmf(EntityManagerFactory emf) {
        this.emf = emf
    }

    // queries: no transaction

    @Override
    List<Product> listAll() {
        // EntityManagerFactory is threadsafe,
        // but the EntityManager is not!!
        EntityManager em = emf.createEntityManager()
        em.createQuery("FROM Product", Product.class).getResultList()
    }

    @Override
    Product getById(Integer id) {
        EntityManager em = emf.createEntityManager()
        em.find(Product.class, id)
    }

    // here transaction counts!

    @Override
    Product saveOrUpdate(Product domainObject) {
        EntityManager em = emf.createEntityManager()
        em.getTransaction().begin()
        Product savedProduct = em.merge(domainObject) // merge() = persist() and "update()"
        em.getTransaction().commit()
        return savedProduct
    }

    @Override
    void delete(Integer id) {
        EntityManager em = emf.createEntityManager()
        em.getTransaction().begin()
        em.remove(em.find(Product.class, id))
        em.getTransaction().commit()
    }
}
