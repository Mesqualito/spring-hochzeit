package rocks.gebsattel.hochzeit.services.reposervices

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import rocks.gebsattel.hochzeit.domain.User
import rocks.gebsattel.hochzeit.repositories.UserRepository
import rocks.gebsattel.hochzeit.services.UserService

@Service
@Profile("springdatajpa")
class UserServiceRepoImpl implements UserService {

    UserRepository userRepository

    @Autowired
    void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository
    }

    @Override
    List<?> listAll(){
        List<User> users = new ArrayList<>()
        userRepository.findAll().each{ users.add(it) }
    }

    @Override
    User getById(Integer id) { userRepository.findById(id).get() }

    @Override
    User saveOrUpdate(User domainObject) { userRepository.save(domainObject) }

    @Override
    void delete(Integer id) { userRepository.delete(id) }
}
