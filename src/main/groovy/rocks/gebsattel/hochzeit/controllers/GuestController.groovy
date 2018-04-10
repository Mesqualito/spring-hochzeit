package rocks.gebsattel.hochzeit.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import rocks.gebsattel.hochzeit.domain.Guest
import rocks.gebsattel.hochzeit.services.GuestService

@Controller
class GuestController {

    private GuestService guestService

    @Autowired
    void setGuestService(GuestService guestService) {
        this.guestService = guestService
    }

    @RequestMapping("/guests")
    String listGuests(Model model){
        model.addAttribute("guests", guestService.listAllGuests())
        return "guests"
    }

    @RequestMapping("/guest/{id}")
    String getGuest(@PathVariable Integer id, Model model) {
        model.addAttribute("guest", guestService.getGuestById(id))
        return "guest"
    }

    @RequestMapping("/guest/new")
    String newGuest(Model model) {
        model.addAttribute("guest", new Guest())
        return "guestform"
    }

    @RequestMapping(value = "/guest", method = RequestMethod.POST)
    String saveOrUpdateGuest(Guest guest) {
        Guest savedGuest = guestService.saveOrUpdateGuest(guest)
        return "redirect:/guest/" + savedGuest.id
    }

}
