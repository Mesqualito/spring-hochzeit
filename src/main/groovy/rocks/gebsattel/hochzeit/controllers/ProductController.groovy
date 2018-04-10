package rocks.gebsattel.hochzeit.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import rocks.gebsattel.hochzeit.domain.Product
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
    String listProducts(Model model) {
        //...and will return the data from the model
        model.addAttribute("products", productService.listAllProducts())
        return "products"
    }

    @RequestMapping("/product/{id}")
    String getProducts(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productService.getProductById(id))
        return "product"
    }

    @RequestMapping("/product/new")
    String newProduct(Model model) {
        model.addAttribute("product", new Product())
        return "productform"
    }

    @RequestMapping("/product/edit/{id}")
    String edit(@PathVariable Integer id, Model model){
        model.addAttribute("product", productService.getProductById(id))
        return "productform"
    }


    // the RequestMapping, which is the most accurate, will be taken:
    // here e.g. 'http://localhost:8080/product' WITH the request-method of'post'
    // ******************************
    // the request comes in and spring is going to bind the form parameters
    // to our product properties dynamically
    // the property-names in the 'productform'-template have to match
    // everything what is on our 'Product'
    // then the data is passed to the controller-method.
    // The controller calls the ProductService and save it.
    // The controller passes back the 'Product'-Object, so we can read the 'id'

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    String saveOrUpdateProduct(Product Product) {
        Product savedProduct = productService.saveOrUpdateProduct(Product)
        return "redirect:/product/" + savedProduct.getId()
    }

    @RequestMapping("/product/delete/{id}")
    String delete(@PathVariable Integer id) {
        productService.deleteProduct(id)
        return "redirect:/products"
    }
}
