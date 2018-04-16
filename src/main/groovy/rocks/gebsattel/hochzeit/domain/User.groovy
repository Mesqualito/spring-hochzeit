package rocks.gebsattel.hochzeit.domain

import javax.persistence.*

@Entity
class User implements DomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id

    @Version
    Integer version

    String username

    @Transient // temporary in state; not stored to the database
    String password

    String encryptedPassword
    Boolean enabled = true

    // Bi-directional One-to-One-Relationship User <-> Customer
    // database-Updates/Deletes driven primarily from the User-Object
    @OneToOne(cascade = [CascadeType.MERGE, CascadeType.PERSIST])
    Customer customer

    @Override
    Integer getId() {
        return id
    }

    @Override
    void setId(Integer id) {
        this.id = id
    }

    void setCustomer(Customer customer){
        this.customer = customer
        customer.setUser(this)
    }
}
