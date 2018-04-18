package rocks.gebsattel.hochzeit.domain

import javax.persistence.*

@Entity
class Cart implements DomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id

    @Version
    Integer version

    @OneToOne
    User user

    // 'mappedBy' for foreign-key-mapping instead of mappings in an automatic joint-table;
    // also we want all cartDetails deleted if a cart is deleted
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart", orphanRemoval = true)
    List<CartDetail> cartDetails = new ArrayList<>()

    void addCartDetail(CartDetail cartDetail){
        cartDetails.add(cartDetail)
        cartDetail.setCart(this)
    }

    void removeCartDetail(CartDetail cartDetail){
        cartDetail.setCart(null)
        this.cartDetails.remove(cartDetail)
    }

}
