package rocks.gebsattel.hochzeit.services.mapservices

import rocks.gebsattel.hochzeit.domain.DomainObject
import rocks.gebsattel.hochzeit.domain.User
import rocks.gebsattel.hochzeit.services.UserService

class UserServiceMapImpl extends AbstractMapService implements UserService {

    @Override
    List<DomainObject> listAll(){
        super.listAll()
    }

    @Override
    User getById(Integer id){
        (User) super.getById(id)
    }

    @Override
    User saveOrUpdate(User domainObject) {
        (User) super.saveOrUpdate(domainObject)
    }

    @Override
    void delete(Integer id){
        super.delete(id)
    }

}
