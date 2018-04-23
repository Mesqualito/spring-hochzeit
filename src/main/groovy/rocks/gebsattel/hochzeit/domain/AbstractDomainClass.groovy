package rocks.gebsattel.hochzeit.domain

import javax.persistence.*

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
    Integer getId() { return this.id }

    @Override
    void setId(Integer id) { this.id = id }

    @PreUpdate
    @PrePersist
    void updateTimeStamps() {
        lastUpdated = new Date()
        if (dateCreated == null) {
            // dateCreated = lastUpdated.clone()
            dateCreated = new Date(lastUpdated.getTime())
        }
    }
}
