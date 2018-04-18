package rocks.gebsattel.hochzeit.bootstrap

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component
import rocks.gebsattel.hochzeit.domain.Address
import rocks.gebsattel.hochzeit.domain.Customer
import rocks.gebsattel.hochzeit.domain.Product
import rocks.gebsattel.hochzeit.services.CustomerService
import rocks.gebsattel.hochzeit.services.ProductService

// "Pull yourself up by your bootstraps" - what you do in the morning of the old days...

@Component
class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private ProductService productService
    private CustomerService customerService

    @Autowired
    void setProductService(ProductService productService) {
        this.productService = productService
    }

    @Autowired
    void setCustomerService(CustomerService customerService) {
        this.customerService = customerService
    }

    @Override
    void onApplicationEvent(ContextRefreshedEvent event) {
        loadCustomers()
        loadProducts()
    }

    void loadCustomers() {

        Customer customer1 = new Customer()
        customer1.setFirstName("Hannes")
        customer1.setLastName("Burgermeister")
        customer1.setBillingAddress(new Address())
        customer1.getBillingAddress().setAddressLine1("Pubsstr. 15")
        customer1.getBillingAddress().setAddressLine2("Nebenhaus")
        customer1.getBillingAddress().setCity("München")
        customer1.getBillingAddress().setState("Bayern")
        customer1.getBillingAddress().setZipCode("28271")
        customer1.seteMail("wunder@pastillen.com")
        customer1.setPhoneNr("089/433284323-21")
        customerService.saveOrUpdate(customer1)

        Customer customer2 = new Customer()
        customer2.setFirstName("Robert")
        customer2.setLastName("Müller")
        customer2.setBillingAddress(new Address())
        customer2.getBillingAddress().setAddressLine1("Am Ampelchen 4")
        customer2.getBillingAddress().setAddressLine2("Untergeschoß")
        customer2.getBillingAddress().setCity("Habsburg")
        customer2.getBillingAddress().setState("Ruhnewald")
        customer2.getBillingAddress().setZipCode("98111")
        customer2.seteMail("r.mueller@gmx.me")
        customer2.setPhoneNr("09383/123-456 78")
        customerService.saveOrUpdate(customer2)

        Customer customer3 = new Customer()
        customer3.setFirstName("Aldi")
        customer3.setLastName("Wachau")
        customer3.setBillingAddress(new Address())
        customer3.getBillingAddress().setAddressLine1("Landstr. 27")
        customer3.getBillingAddress().setAddressLine2("Kellereingang")
        customer3.getBillingAddress().setCity("Nürnberg")
        customer3.getBillingAddress().setState("Unterfranken")
        customer3.getBillingAddress().setZipCode("90931")
        customer3.seteMail("aldi@wachau.rocks")
        customer3.setPhoneNr("0911/983461")
        customerService.saveOrUpdate(customer3)
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
