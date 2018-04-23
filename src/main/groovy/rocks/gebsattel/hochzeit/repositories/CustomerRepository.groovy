package rocks.gebsattel.hochzeit.repositories

import org.springframework.data.repository.CrudRepository
import rocks.gebsattel.hochzeit.domain.Customer

interface CustomerRepository extends CrudRepository<Customer, Integer> {

}