package rocks.gebsattel.hochzeit.services

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import rocks.gebsattel.hochzeit.domain.Product

@RunWith(SpringJUnit4ClassRunner)
@SpringBootTest("JpaIntegrationConfig")
@ActiveProfiles("jpadao") // keep test independent from application's "application properties"!
class ProductServiceJpaDaoImplTest {

    private ProductService productService

    @Autowired
    void setProductService(ProductService productService) {
        this.productService = productService
    }

    @Test
    void testListMethod() throws Exception {

        List <Product> products = (List<Product>) productService.listAll()
        assert products.size() == 5
    }
}
