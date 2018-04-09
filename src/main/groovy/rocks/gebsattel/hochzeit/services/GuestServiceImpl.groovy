package rocks.gebsattel.hochzeit.services

import org.springframework.stereotype.Service
import rocks.gebsattel.hochzeit.domain.Guest

@Service
class GuestServiceImpl implements GuestService {

    private Map<Integer,Guest> guests

    GuestServiceImpl(){
        loadGuests()
    }

    @Override
    Guest getGuestById(Integer id) {
        return guests.get(id)
    }

    @Override
    List<Guest> listAllGuests() {
        return new ArrayList<>(guests.values())



    }

    private void loadGuests() {

        guests = new HashMap<>()

        Guest guest1 = new Guest()
        guest1.setId(1)
        guest1.setFamilyName("Fischer")
        guest1.setFirstName("Robert")
        guest1.setTitle("Herr")
        guest1.seteMail("testmail@pseudodomain.de")
        guest1.setCode("RoFixv146")
        guest1.setImgUrl("http://hochzeit.gebsattel.rocks/bilder/1.robert.fischer.jpg")
        guest1.setStreet("Ulmenweg")
        guest1.setStreetNr("5")
        guest1.setZipCode("96123")
        guest1.setCity("Litzendorf/Naisa")
        guest1.setPhoneNr("1234568-212432")

        guests.put(1, guest1)

        Guest guest2 = new Guest()
        guest2.setId(2)
        guest2.setFamilyName("Marquard")
        guest2.setFirstName("Martin")
        guest2.setTitle("Herr")
        guest2.seteMail("test@gibtsnet.de")
        guest2.setCode("MaMaxv138")
        guest2.setImgUrl("http://hochzeit.gebsattel.rocks/bilder/2.martin.marquard.jpg")
        guest2.setStreet("Raiffeisenstr.")
        guest2.setStreetNr("20")
        guest2.setZipCode("97334")
        guest2.setCity("Nordheim")
        guest2.setPhoneNr("09381-123456")

        guests.put(2, guest2)



    }

}
