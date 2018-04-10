package rocks.gebsattel.hochzeit.services

import rocks.gebsattel.hochzeit.domain.Guest

interface GuestService {

    List<Guest> listAllGuests()

    Guest getGuestById(Integer id)

    Guest saveOrUpdateGuest(Guest guest)
}
