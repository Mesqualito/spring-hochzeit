package rocks.gebsattel.hochzeit.services

import rocks.gebsattel.hochzeit.commands.ProductForm
import rocks.gebsattel.hochzeit.domain.Product

interface ProductService extends CRUDService<Product> {

    Product saveOrUpdateProductForm(ProductForm productForm)

}