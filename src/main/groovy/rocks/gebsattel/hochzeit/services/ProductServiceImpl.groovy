package rocks.gebsattel.hochzeit.services

import org.springframework.stereotype.Service
import rocks.gebsattel.hochzeit.domain.Product

// for now: our model class
// here is the place to get data per jsp, different database-service-classes etc.
// NO jsp-/database-code in the view-layer or in the controller-layer

// create Spring-Bean and implement ProductServiceImpl as a 'Spring-Service'
@Service
class ProductServiceImpl implements ProductService {

    private Map<Integer, Product> products

    ProductServiceImpl(){
        loadProducts()
    }

    @Override
    List<Product> listAllProducts() {
        new ArrayList<>(products.values())
    }

    @Override
    Product getProductById(Integer id) {
        return products.get(id)
    }

    @Override
    Product saveOrUpdateProduct(Product product) {
        if (product != null) {
            if (product.getId() == null) {
                product.setId(getNextKey())
            }
            products.put(product.getId(), product)
            return product
        } else {
            throw new RuntimeException("Product can't be NULL")
        }
    }

    private Integer getNextKey(){
        return Collections.max(products.keySet()) + 1
    }

    private void loadProducts(){
        products = new HashMap<>()

        Product product1 = new Product()
        product1.setId(1)
        product1.setDescription("Product Nr. 1")
        product1.setPrice(new BigDecimal("12.01"))
        product1.setImageUrl("http://hochzeit.gebsattel.rocks/product1")

        products.put(1, product1)


        Product product2 = new Product()
        product1.setId(2)
        product1.setDescription("Product Nr. 2")
        product1.setPrice(new BigDecimal("12.02"))
        product1.setImageUrl("http://hochzeit.gebsattel.rocks/product2")

        products.put(2, product2)


        Product product3 = new Product()
        product1.setId(3)
        product1.setDescription("Product Nr. 3")
        product1.setPrice(new BigDecimal("12.03"))
        product1.setImageUrl("http://hochzeit.gebsattel.rocks/product3")

        products.put(3, product3)


        Product product4 = new Product()
        product1.setId(4)
        product1.setDescription("Product Nr. 4")
        product1.setPrice(new BigDecimal("12.04"))
        product1.setImageUrl("http://hochzeit.gebsattel.rocks/product4")

        products.put(4, product4)


        Product product5 = new Product()
        product1.setId(5)
        product1.setDescription("Product Nr. 5")
        product1.setPrice(new BigDecimal("12.05"))
        product1.setImageUrl("http://hochzeit.gebsattel.rocks/product5")

        products.put(5, product5)


    }

}
