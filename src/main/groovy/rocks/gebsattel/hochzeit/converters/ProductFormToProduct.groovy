package rocks.gebsattel.hochzeit.converters

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import rocks.gebsattel.hochzeit.commands.ProductForm
import rocks.gebsattel.hochzeit.domain.Product

@Component
class ProductFormToProduct implements Converter<ProductForm, Product> {

    @Override
    Product convert(ProductForm productForm) {
        Product product = new Product()
        product.setId(productForm.getId())
        product.setVersion(productForm.getVersion())
        product.setDescription(productForm.getDescription())
        product.setPrice(productForm.getPrice())
        product.setImageUrl(productForm.getImageUrl())
        return product
    }
}
