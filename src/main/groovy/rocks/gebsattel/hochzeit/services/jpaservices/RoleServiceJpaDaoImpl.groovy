package rocks.gebsattel.hochzeit.services.jpaservices

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import rocks.gebsattel.hochzeit.domain.security.Role
import rocks.gebsattel.hochzeit.services.RoleService

import javax.persistence.EntityManager

@Service
@Profile("jpadao")
class RoleServiceJpaDaoImpl extends AbstractJpaDaoService implements RoleService {

    @Override
    List<?> listAll() {
        EntityManager em = emf.createEntityManager()
        em.createQuery("FROM Role", Role.class).getResultList()
    }

    @Override
    Role getById(Integer id) {
        EntityManager em = emf.createEntityManager()
        em.find(Role.class, id)
    }

    @Override
    Role saveOrUpdate(Role domainObject) {
        EntityManager em = emf.createEntityManager()

        em.getTransaction().begin()
        Role saveRole = em.merge(domainObject)
        em.getTransaction().commit()

        return saveRole
    }

    @Override
    void delete(Integer id) {
        EntityManager em = emf.createEntityManager()

        em.getTransaction().begin()
        em.remove(em.find(Role.class, id))
        em.getTransaction().commit()
    }
}
