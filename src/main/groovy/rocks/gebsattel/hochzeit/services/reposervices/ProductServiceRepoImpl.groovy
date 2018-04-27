package rocks.gebsattel.hochzeit.services.reposervices

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import rocks.gebsattel.hochzeit.commands.ProductForm
import rocks.gebsattel.hochzeit.converters.ProductFormToProduct
import rocks.gebsattel.hochzeit.converters.ProductToProductForm
import rocks.gebsattel.hochzeit.domain.Product
import rocks.gebsattel.hochzeit.repositories.ProductRepository
import rocks.gebsattel.hochzeit.services.ProductService

@Service
@Profile("springdatajpa")
class ProductServiceRepoImpl implements ProductService {

    ProductRepository productRepository
    ProductToProductForm productToProductForm
    ProductFormToProduct productFormToProduct

    @Autowired
    void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository
    }

    @Autowired
    void setProductToProductForm(ProductToProductForm productToProductForm) {
        this.productToProductForm = productToProductForm
    }

    @Autowired
    void setProductFormToProduct(ProductFormToProduct productFormToProduct) {
        this.productFormToProduct = productFormToProduct
    }

    @Override
    List<?> listAll() {
        List<Product> products = new ArrayList<>()
        // Java 8: Method references:
        // productRepository.findAll().forEach(products::add)
        productRepository.findAll().each { products.add(it) }
        return products
    }

    @Override
    Product getById(Integer id) {
        // productRepository.findOne(id)
        productRepository.findById(id).get()
    }

    @Override
    Product saveOrUpdate(Product domainObject) {
        productRepository.save(domainObject)
    }

    @Override
    void delete(Integer id) {
        productRepository.deleteById(id)
    }

    // just to have an own github-Commit - to remember this step -
    // I returned to the old code
    @Override
    ProductForm saveOrUpdate(ProductForm productForm) {

        if (productForm.getId() != null) { // existing product
            Product productToUpdate = this.getById(productForm.getId())

            productToUpdate.setVersion(productForm.getVersion())
            productToUpdate.setDescription(productForm.getDescription())
            productToUpdate.setPrice(productForm.getPrice())
            productToUpdate.setImageUrl(productForm.getImageUrl())

            return productToProductForm.convert(this.saveOrUpdate(productToUpdate))

        } else { // product is new
            return productToProductForm.convert(this.saveOrUpdate(productFormToProduct.convert(productForm)))
        }
    }
}
