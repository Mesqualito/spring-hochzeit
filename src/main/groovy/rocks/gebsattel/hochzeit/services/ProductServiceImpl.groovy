package rocks.gebsattel.hochzeit.services

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import rocks.gebsattel.hochzeit.domain.DomainObject
import rocks.gebsattel.hochzeit.domain.Product

@Service
@Profile("map")
class ProductServiceImpl extends AbstractMapService implements ProductService {

    @Override
    List<DomainObject> listAll() { super.listAll() }

    @Override
    Product getById(Integer id) { (Product) super.getById(id) }

    @Override
    Product saveOrUpdate(Product domainObject) { (Product) super.saveOrUpdate(domainObject) }

    @Override
    void delete(Integer id) { super.delete(id) }

    @Override
    protected void loadDomainObjects() {
        domainMap = new HashMap<>()

        Product product1 = new Product()
        product1.setId(1)
        product1.setDescription("Product Nr. 1")
        product1.setPrice(new BigDecimal("12.01"))
        product1.setImageUrl("http://hochzeit.gebsattel.rocks/product1")

        domainMap.put(1, product1)

        Product product2 = new Product()
        product2.setId(2)
        product2.setDescription("Product Nr. 2")
        product2.setPrice(new BigDecimal("12.02"))
        product2.setImageUrl("http://hochzeit.gebsattel.rocks/product2")

        domainMap.put(2, product2)

        Product product3 = new Product()
        product3.setId(3)
        product3.setDescription("Product Nr. 3")
        product3.setPrice(new BigDecimal("12.03"))
        product3.setImageUrl("http://hochzeit.gebsattel.rocks/product3")

        domainMap.put(3, product3)

        Product product4 = new Product()
        product4.setId(4)
        product4.setDescription("Product Nr. 4")
        product4.setPrice(new BigDecimal("12.04"))
        product4.setImageUrl("http://hochzeit.gebsattel.rocks/product4")

        domainMap.put(4, product4)

        Product product5 = new Product()
        product5.setId(5)
        product5.setDescription("Product Nr. 5")
        product5.setPrice(new BigDecimal("12.05"))
        product5.setImageUrl("http://hochzeit.gebsattel.rocks/product5")

        domainMap.put(5, product5)

        assert domainMap.size() == 5
        println "\'domainMap.size()' (Products) = ${domainMap.size()}"
   }

}
