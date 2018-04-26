package rocks.gebsattel.hochzeit.commands

import org.hibernate.validator.constraints.URL

import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class ProductForm {

    Integer id
    Integer version

    @NotEmpty
    @Size(min = 5, max = 200)
    String description

    @NotNull
    @Min(0L)
    @Max(5000L)
    BigDecimal price

    @NotEmpty
    @URL
    String imageUrl

}
