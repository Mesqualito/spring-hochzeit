package rocks.gebsattel.hochzeit.domain

import javax.persistence.*

@Entity
class Customer extends AbstractDomainClass implements DomainObject {

    String firstName
    String lastName
    String eMail
    String phoneNr

    @Embedded
    Address billingAddress

    @Embedded
    Address shippingAddress

    @OneToOne(cascade = [ CascadeType.MERGE, CascadeType.PERSIST ])
    User user

    // @Override
    // Integer getId() {
    //    return id;
    // }
}
