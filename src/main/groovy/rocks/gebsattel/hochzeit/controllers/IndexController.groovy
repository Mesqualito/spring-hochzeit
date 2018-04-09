package rocks.gebsattel.hochzeit.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class IndexController {

    // where should the controller listen?
    @RequestMapping("/")
    String index(){
        // by convention: we return 'index' with the controller
        // to spring-mvc to tell what view we want to see
        // spring-mvc then looks for our thymeleaf-template called 'index'
        // in 'templates' !! '.html' is implied !!
        return "index"
    }
}
