package rocks.gebsattel.hochzeit.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import rocks.gebsattel.hochzeit.domain.Product
import rocks.gebsattel.hochzeit.services.ProductService

@RequestMapping("/product")
@Controller
class ProductController {

    private ProductService productService

    @Autowired
    void setProductService(ProductService productService) {
        this.productService = productService
    }

    @RequestMapping( ["/list","/"] )
    String listProducts(Model model) {
        //...and will return the data from the model
        model.addAttribute("products", productService.listAll())
        return "product/list"
    }

    @RequestMapping("/show/{id}")
    String getProducts(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productService.getById(id))
        return "product/show"
    }

    @RequestMapping("/edit/{id}")
    String edit(@PathVariable Integer id, Model model){
        model.addAttribute("product", productService.getById(id))
        return "product/productform"
    }

    @RequestMapping("/new")
    String newProduct(Model model) {
        model.addAttribute("product", new Product())
        return "product/productform"
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    String saveOrUpdateProduct(Product Product) {
        Product savedProduct = productService.saveOrUpdate(Product)
        return "redirect:/product/show/" + savedProduct.getId()
    }

    @RequestMapping("/delete/{id}")
    String delete(@PathVariable Integer id) {
        productService.delete(id)
        return "redirect:/product/list"
    }
}
