package rocks.gebsattel.hochzeit.services.jpaservices

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import rocks.gebsattel.hochzeit.domain.Product
import rocks.gebsattel.hochzeit.services.ProductService

import javax.persistence.EntityManager

@Service
@Profile("jpadao")
class ProductServiceJpaDaoImpl extends AbstractJpaDaoService implements ProductService {

    @Override
    List<Product> listAll() {
        EntityManager em = emf.createEntityManager()
        em.createQuery("FROM Product", Product.class).getResultList()
    }

    @Override
    Product getById(Integer id) {
        EntityManager em = emf.createEntityManager()
        em.find(Product.class, id)
    }

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
