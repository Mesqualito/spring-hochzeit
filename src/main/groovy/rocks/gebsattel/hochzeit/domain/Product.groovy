package rocks.gebsattel.hochzeit.domain

import javax.persistence.*

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
