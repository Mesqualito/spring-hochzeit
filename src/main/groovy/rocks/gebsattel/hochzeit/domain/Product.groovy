package rocks.gebsattel.hochzeit.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Version

@Entity
class Product implements DomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id

    @Version // JPA Optimistic Locking - no lost updates, when two or more user take one object/record to change
    Integer version

    String description
    BigDecimal price
    String imageUrl

}
