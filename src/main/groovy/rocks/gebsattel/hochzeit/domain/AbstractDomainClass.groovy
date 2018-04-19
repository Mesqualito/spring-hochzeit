package rocks.gebsattel.hochzeit.domain

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass
import javax.persistence.PrePersist
import javax.persistence.PreUpdate
import javax.persistence.Version

// tells JPA, we will inherite properties from this class
@MappedSuperclass
class AbstractDomainClass implements DomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id

    @Version
    Integer version

    Date dateCreated
    Date lastUpdated

    @Override
    Integer getId(){ return this.id }

    @Override
    void setId(Integer id) { this.id = id}

    @PreUpdate
    @PrePersist
    void updateTimeStamps(){
        if(dateCreated == null){
            dateCreated = new Date()
        }
        lastUpdated = new Date()
    }
}
