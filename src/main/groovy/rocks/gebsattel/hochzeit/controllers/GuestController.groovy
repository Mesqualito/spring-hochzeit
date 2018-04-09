package rocks.gebsattel.hochzeit.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
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

}
