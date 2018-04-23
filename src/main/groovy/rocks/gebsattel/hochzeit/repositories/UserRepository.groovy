package rocks.gebsattel.hochzeit.repositories

import org.springframework.data.repository.CrudRepository
import rocks.gebsattel.hochzeit.domain.User

interface UserRepository extends CrudRepository<User, Integer> {

}