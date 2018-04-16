package rocks.gebsattel.hochzeit.services.jpaservices

import javax.persistence.EntityManagerFactory
import javax.persistence.PersistenceUnit

class AbstractJpaDaoService {

    protected EntityManagerFactory emf

    @PersistenceUnit
    void setEmf(EntityManagerFactory emf){
        this.emf = emf
    }
}
