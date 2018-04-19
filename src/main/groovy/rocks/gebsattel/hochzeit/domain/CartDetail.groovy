package rocks.gebsattel.hochzeit.domain

import javax.persistence.*

@Entity
class CartDetail extends AbstractDomainClass implements DomainObject {

    Integer quantity

    // many cartDetails fit on one cart
    @ManyToOne
    Cart cart

    // Uni-directional relationship:
    // the cartDetail needs products,
    // but products don't need a cart
    @OneToOne
    Product product
}
