package rocks.gebsattel.hochzeit.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import rocks.gebsattel.hochzeit.domain.User
import rocks.gebsattel.hochzeit.services.UserService

@Controller
@RequestMapping("/user")
class UserController {

    UserService userService

    @Autowired
    void setUserService(UserService userService) {
        this.userService = userService
    }

    @RequestMapping(["/list", "/"])
    String listUsers(Model model){
        model.addAttribute("users", userService.listAll())
        return "user/list"
    }

    @RequestMapping("/show/{id}")
    String getUser(@PathVariable Integer id, Model model){
        model.addAttribute("user", userService.getById(id))
        return "user/show"
    }

    @RequestMapping("/edit/{id}")
    String edit(@PathVariable Integer id, Model model){
        model.addAttribute("user", userService.getById(id))
        return "user/userform"
    }

    @RequestMapping("/new")
    String newUser(Model model){
        model.addAttribute("user", new User())
        return "user/userform"
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    String saveOrUpdate(User user){
        User savedUser = userService.saveOrUpdate(user)
        return "redirect:/user/show" + savedUser.getId()
    }

    @RequestMapping("/delete/{id}")
    String delete(@PathVariable Integer id){
        userService.delete(id)
        return "redirect:/user/list"
    }
}
