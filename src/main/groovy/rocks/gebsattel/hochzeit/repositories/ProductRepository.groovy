package rocks.gebsattel.hochzeit.repositories

import org.springframework.data.repository.CrudRepository
import rocks.gebsattel.hochzeit.domain.Product

interface ProductRepository extends CrudRepository<Product, Integer> {
}
