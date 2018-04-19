package rocks.gebsattel.hochzeit.domain

import javax.persistence.*

@Entity
class Product extends AbstractDomainClass implements DomainObject {

    String description
    BigDecimal price
    String imageUrl

}
