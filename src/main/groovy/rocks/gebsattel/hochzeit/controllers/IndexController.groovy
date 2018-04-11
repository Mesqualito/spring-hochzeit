package rocks.gebsattel.hochzeit.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class IndexController {

    // where should the controller listen?
    @RequestMapping("/")
    String index(){
        return "index"
    }
}
