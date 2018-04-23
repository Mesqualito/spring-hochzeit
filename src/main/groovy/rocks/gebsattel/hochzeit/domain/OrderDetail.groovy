package rocks.gebsattel.hochzeit.domain

import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.OneToOne

@Entity
class OrderDetail extends AbstractDomainClass {

    @ManyToOne
    Order order

    // product does not need to know about orderlines:
    // unidirectional relationship
    @OneToOne
    Product product

    Integer quantity

}
