package rocks.gebsattel.hochzeit.services

import rocks.gebsattel.hochzeit.domain.Guest

interface GuestService {

    List<Guest> listAllGuests()

    Guest getGuestById(Integer id)

    Guest saveGuest(Guest guest)

    Guest updateGuest(Guest guest)

    Guest proposeGuest(Guest guest)

    void deleteGuest(Integer id)

}
