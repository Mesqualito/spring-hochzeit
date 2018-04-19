package rocks.gebsattel.hochzeit.domain

import javax.persistence.*

@Entity
class User extends AbstractDomainClass implements DomainObject {

    String username

    @Transient // temporary in state; not stored to the database
    String password

    String encryptedPassword
    Boolean enabled = true

    // Bi-directional One-to-One-Relationship User <-> Customer
    // database-Updates/Deletes driven primarily from the User-Object
    @OneToOne(cascade = [CascadeType.MERGE, CascadeType.PERSIST])
    Customer customer

    // cart-Entity get deleted if user will be deleted, and also orphans
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    Cart cart

    void setCustomer(Customer customer){
        this.customer = customer
        customer.setUser(this)
    }
}
