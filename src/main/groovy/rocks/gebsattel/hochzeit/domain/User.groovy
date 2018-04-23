package rocks.gebsattel.hochzeit.domain

import rocks.gebsattel.hochzeit.domain.security.Role

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

    @ManyToMany
    @JoinTable
    // ~ defaults to @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "user_id"),
    //               inverseJoinColumns = @joinColumn(name = "role_id"))
    List<Role> roles = new ArrayList<>()

    void setCustomer(Customer customer){
        this.customer = customer
        customer.setUser(this)
    }

    void addRole(Role role){
        if(!this.roles.contains(role)){
            this.roles.add(role)
        }

        if(!role.getUsers().contains(this)){
            role.getUsers().add(this)
        }
    }

    void removeRole(Role role){
        this.roles.remove(role)
        role.getUsers().remove(this)
    }


}
