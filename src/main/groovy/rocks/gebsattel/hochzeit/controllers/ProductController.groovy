package rocks.gebsattel.hochzeit.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import rocks.gebsattel.hochzeit.services.ProductService

// Controller should look very simple:
// a controller will be asked for data from "the view",
// knows, where to get which data from which services
// and asks a model in the correct manner for the data
// NO database connection with a controller etc. !

// Spring Bean notice: here is a Controller Class for your projects spring framework
@Controller
class ProductController {

    private ProductService productService


    // Spring Bean 'ProductService' being injected "by Setter"
    // on method call; and here "by Type", because 'ProductServiceImpl' is the called class
    // with its type 'ProductService'
    @Autowired
    void setProductService(ProductService productService) {
        this.productService = productService
    }

    // will be asked by the dispatch servlet...
    @RequestMapping("/products")
    String listProducts(Model model){
        //...and will return the data from the model
        model.addAttribute("products", productService.listAllProducts())

        return "products"
    }


}
