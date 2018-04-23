package rocks.gebsattel.hochzeit.repositories

import org.springframework.data.repository.CrudRepository
import rocks.gebsattel.hochzeit.domain.security.Role

interface RoleRepository extends CrudRepository<Role, Integer> {

}