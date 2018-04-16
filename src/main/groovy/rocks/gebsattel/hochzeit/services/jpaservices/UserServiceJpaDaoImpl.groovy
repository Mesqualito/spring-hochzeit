package rocks.gebsattel.hochzeit.services.jpaservices

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import rocks.gebsattel.hochzeit.domain.User
import rocks.gebsattel.hochzeit.services.UserService
import rocks.gebsattel.hochzeit.services.security.EncryptionService

import javax.persistence.EntityManager

@Service
@Profile("jpadao")
class UserServiceJpaDaoImpl extends AbstractJpaDaoService implements UserService {

    private EncryptionService encryptionService

    @Autowired
    void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService
    }

    @Override
    List<?> listAll(){
        EntityManager em = emf.createEntityManager()
        em.createQuery("FROM User", User.class).getResultList()
    }

    @Override
    User getById(Integer id) {
        EntityManager em = emf.createEntityManager()
        em.find(User.class, id)
    }

    @Override
    User saveOrUpdate(User domainObject){
        EntityManager em = emf.createEntityManager()
        em.getTransaction().begin()
        if(domainObject.getPassword() != null){
            domainObject.setEncryptedPassword(encryptionService.encryptString(domainObject.getPassword()))
        }

        User savedUser = em.merge(domainObject)
        em.getTransaction().commit()
        return savedUser
    }

    @Override
    void delete(Integer id){
        EntityManager em = emf.createEntityManager()
        em.getTransaction().begin()
        em.remove(em.find(User.class, id))
        em.getTransaction().commit()
    }

}
