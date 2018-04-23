package rocks.gebsattel.hochzeit.domain.security

import rocks.gebsattel.hochzeit.domain.AbstractDomainClass
import rocks.gebsattel.hochzeit.domain.User

import javax.persistence.Entity
import javax.persistence.JoinTable
import javax.persistence.ManyToMany

@Entity
class Role extends AbstractDomainClass {

    String role

    // bi-directional with a JoinTable
    // many Users can have many Roles, no cascading delete...
    @ManyToMany
    @JoinTable
    // ~ defaults to @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "role_id"),
    //               inverseJoinColumns = @joinColumn(name = "user_id"))
    List<User> users = new ArrayList<>()

    void addUser(User user){
        if(!this.users.contains(user)){
            this.users.add(user)
        }

        // for bi-directional habbit:
        if(!user.getRoles().contains(this)){
            user.getRoles().add(this)
        }
    }

    void removeUser(User user){
        this.users.remove(user)
        user.getRoles().remove(this)
    }
}
