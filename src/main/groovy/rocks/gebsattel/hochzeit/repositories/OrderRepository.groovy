package rocks.gebsattel.hochzeit.repositories

import org.springframework.data.repository.CrudRepository
import rocks.gebsattel.hochzeit.domain.Order

interface OrderRepository extends CrudRepository<Order, Integer> {

}