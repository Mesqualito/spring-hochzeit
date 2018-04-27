package rocks.gebsattel.hochzeit.services

import rocks.gebsattel.hochzeit.commands.ProductForm
import rocks.gebsattel.hochzeit.domain.Product

interface ProductService extends CRUDService<Product> {

    // that does never fit to the construct between Interface and Implementations in (and the step before)
    // https://github.com/springframeworkguru/spring-core-spring-mvc/blob/spring-mvc-command-obj-code-assignment-review/src/main/java/guru/springframework/services/ProductService.java
    // I will keep this on github to rethink it some day
    Product saveOrUpdateProductForm(ProductForm productForm)

}