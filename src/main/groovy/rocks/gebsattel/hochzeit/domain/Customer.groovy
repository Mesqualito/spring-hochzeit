package rocks.gebsattel.hochzeit.domain

import javax.persistence.*

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

    @Embedded
    Address billingAddress

    @Embedded
    Address shippingAddress

    // @OneToOne(cascade = CascadeType.ALL) // only if deletion of Customer should walk through and delete User also
    // database-Updates/Deletes driven primarily from the User-Object
    @OneToOne
    User user

    @Override
    Integer getId() {
        return id
    }

    @Override
    void setId(Integer id) {
        this.id = id
    }
}
