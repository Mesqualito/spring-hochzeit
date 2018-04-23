package rocks.gebsattel.hochzeit.services.reposervices

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import rocks.gebsattel.hochzeit.domain.Product
import rocks.gebsattel.hochzeit.repositories.ProductRepository
import rocks.gebsattel.hochzeit.services.ProductService

@Service
@Profile(["springdatajpa", "jpadao"])
class ProductServiceRepoImpl implements ProductService {

    ProductRepository productRepository

    @Autowired
    void setProductRepository(ProductRepository productRepository){
        this.productRepository = productRepository
    }

    @Override
    List<?> listAll() {
        List<Product> products = new ArrayList<>()
        // Java 8: Method references:
        // productRepository.findAll().forEach(products::add)
        productRepository.findAll().each{ products.add(it) }
        return products
    }

    @Override
    Product getById(Integer id){
        // productRepository.findOne(id)
        productRepository.findById(id)
    }

    @Override
    Product saveOrUpdate(Product domainObject) {
        productRepository.save(domainObject)
    }

    @Override
    void delete(Integer id) {
        productRepository.delete(id)
    }
}
