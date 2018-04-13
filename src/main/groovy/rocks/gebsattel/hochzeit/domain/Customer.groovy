package rocks.gebsattel.hochzeit.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Version

@Entity
class Customer implements DomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id

    @Version
    Integer version

    String firstName
    String lastName
    String eMail
    String phoneNr
    String addressLine1
    String addressLine2
    String city
    String state
    String zipCode

    @Override
    Integer getId() {
        return id
    }

    @Override
    void setId(Integer id) {
        this.id = id
    }
}
