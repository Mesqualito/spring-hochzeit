package rocks.gebsattel.hochzeit.domain

import javax.persistence.*

@Entity
class CartDetail implements DomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id

    @Version
    Integer version

    // many cartDetails fit on one cart
    @ManyToOne
    Cart cart

    // Uni-directional relationship:
    // the cartDetail needs products,
    // but products don't need a cart
    @OneToOne
    Product product

    @Override
    Integer getId(){
        return id
    }

    @Override
    void setId(Integer id){
        this.id = id
    }
}
