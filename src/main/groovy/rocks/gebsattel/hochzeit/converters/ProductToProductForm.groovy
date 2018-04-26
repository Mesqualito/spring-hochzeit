package rocks.gebsattel.hochzeit.converters

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import rocks.gebsattel.hochzeit.commands.ProductForm
import rocks.gebsattel.hochzeit.domain.Product

@Component
class ProductToProductForm implements Converter<Product, ProductForm> {

    @Override
    ProductForm convert(Product product){
        ProductForm productForm = new ProductForm()
        productForm.setId(product.getId())
        productForm.setVersion(product.getVersion())
        productForm.setDescription(product.getDescription())
        productForm.setPrice(product.getPrice())
        productForm.setImageUrl(product.getImageUrl())
        return productForm
    }
}
