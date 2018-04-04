package rocks.gebsattel.hochzeit.services

import rocks.gebsattel.hochzeit.domain.Product

interface ProductService {

    List<Product> listAllProducts()

    Product getProductById(Integer id)

}