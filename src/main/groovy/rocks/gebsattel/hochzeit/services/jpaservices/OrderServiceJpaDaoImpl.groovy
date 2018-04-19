package rocks.gebsattel.hochzeit.services.jpaservices

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import rocks.gebsattel.hochzeit.domain.Order
import rocks.gebsattel.hochzeit.services.OrderService

import javax.persistence.EntityManager

@Service
@Profile("jpadao")
class OrderServiceJpaDaoImpl extends AbstractJpaDaoService implements OrderService {

    @Override
    List<Order> listAll() {
        EntityManager em = emf.createEntityManager()
        return em.createQuery("FROM Order", Order.class).getResultList()
    }

    @Override
    Order getById(Integer id) {
        EntityManager em = emf.createEntityManager()

        em.getTransaction().begin()
        Order savedProduct = em.merge(domainObject)
        em.getTransaction().commit()

        return savedProduct
    }

    @Override
    Order saveOrUpdate(Order domainObject) {
        EntityManager em = emf.createEntityManager()

        em.getTransaction().begin()
        Order savedProduct = em.merge(domainObject)
        em.getTransaction().commit()

        return savedProduct
    }

    @Override
    void delete(Integer id) {
        EntityManager em = emf.createEntityManager()

        em.getTransaction().begin()
        em.remove(em.find(Order.class, id))
        em.getTransaction().commit()
    }
}
