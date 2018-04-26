package rocks.gebsattel.hochzeit.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import rocks.gebsattel.hochzeit.commands.ProductForm
import rocks.gebsattel.hochzeit.converters.ProductToProductForm
import rocks.gebsattel.hochzeit.domain.Product
import rocks.gebsattel.hochzeit.services.ProductService

import javax.validation.Valid

@RequestMapping("/product")
@Controller
class ProductController {

    private ProductService productService
    private ProductToProductForm productToProductForm

    @Autowired
    void setProductService(ProductService productService) {
        this.productService = productService
    }

    @Autowired
    void setProductToProductForm(ProductToProductForm productToProductForm) {
        this.productToProductForm = productToProductForm
    }

    @RequestMapping( ["/list","/"] )
    String listProducts(Model model) {
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
        Product product = productService.getById(id)
        ProductForm productForm = productToProductForm.convert(product)

        model.addAttribute("productForm", productForm)

        return "product/productform"
    }

    @RequestMapping("/new")
    String newProduct(Model model) {
        model.addAttribute("productForm", new ProductForm())
        return "product/productform"
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    String saveOrUpdateProduct(@Valid ProductForm productForm, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return "product/productform"
        }

        Product savedProduct = productService.saveOrUpdateProductForm(productForm)
        return "redirect:/product/show/" + savedProduct.getId()
    }

    @RequestMapping("/delete/{id}")
    String delete(@PathVariable Integer id) {
        productService.delete(id)
        return "redirect:/product/list"
    }
}
