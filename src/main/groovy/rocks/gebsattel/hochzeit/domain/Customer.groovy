package rocks.gebsattel.hochzeit.domain

class Customer implements DomainObject {

    Integer id
    String firstName
    String lastName
    String eMail
    String phoneNr
    String addressLine1
    String addressLine2
    String city
    String state
    String zipCode

    @Override
    Integer getId() {
        return id
    }

    @Override
    void setId(Integer id) {
        this.id = id
    }
}
