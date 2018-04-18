package rocks.gebsattel.hochzeit.domain

import javax.persistence.Embeddable

@Embeddable
class Address {

    String addressLine1
    String addressLine2
    String city
    String state
    String zipCode
}
