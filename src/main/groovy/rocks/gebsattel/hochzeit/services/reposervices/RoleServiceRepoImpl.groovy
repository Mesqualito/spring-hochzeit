package rocks.gebsattel.hochzeit.services.reposervices

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import rocks.gebsattel.hochzeit.domain.security.Role
import rocks.gebsattel.hochzeit.repositories.RoleRepository
import rocks.gebsattel.hochzeit.services.RoleService

@Service
@Profile("springdatajpa")
class RoleServiceRepoImpl implements RoleService {

    RoleRepository roleRepository

    @Autowired
    void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository
    }

    @Override
    List<?> listAll(){
        List<Role> roles = new ArrayList<>()
        roleRepository.findAll().each{ roles.add(it) }
        return roles
    }

    @Override
    Role getById(Integer id) { roleRepository.findById(id).get() }

    @Override
    Role saveOrUpdate(Role domainObject) { roleRepository.save(domainObject) }

    @Override
    void delete(Integer id) {
        roleRepository.deleteById(id)
    }
}
