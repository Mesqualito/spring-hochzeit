package rocks.gebsattel.hochzeit.bootstrap

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component
import rocks.gebsattel.hochzeit.domain.Product
import rocks.gebsattel.hochzeit.services.ProductService

// "Pull yourself up by your bootstraps" - what you do in the morning of the old days...

@Component
class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private ProductService productService

    @Autowired
    void setProductService(ProductService productService) {
        this.productService = productService
    }

    @Override
    void onApplicationEvent(ContextRefreshedEvent event) {
        loadProducts()
    }

    void loadProducts() {

        Product product1 = new Product()
        product1.setDescription("Product Nr. 1")
        product1.setPrice(new BigDecimal("12.01"))
        product1.setImageUrl("http://hochzeit.gebsattel.rocks/product1")
        productService.saveOrUpdate(product1)

        Product product2 = new Product()
        product2.setDescription("Product Nr. 2")
        product2.setPrice(new BigDecimal("12.02"))
        product2.setImageUrl("http://hochzeit.gebsattel.rocks/product2")
        productService.saveOrUpdate(product2)

        Product product3 = new Product()
        product3.setDescription("Product Nr. 3")
        product3.setPrice(new BigDecimal("12.03"))
        product3.setImageUrl("http://hochzeit.gebsattel.rocks/product3")
        productService.saveOrUpdate(product3)

        Product product4 = new Product()
        product4.setDescription("Product Nr. 4")
        product4.setPrice(new BigDecimal("12.04"))
        product4.setImageUrl("http://hochzeit.gebsattel.rocks/product4")
        productService.saveOrUpdate(product4)

        Product product5 = new Product()
        product5.setDescription("Product Nr. 5")
        product5.setPrice(new BigDecimal("12.05"))
        product5.setImageUrl("http://hochzeit.gebsattel.rocks/product5")
        productService.saveOrUpdate(product5)
    }
}
