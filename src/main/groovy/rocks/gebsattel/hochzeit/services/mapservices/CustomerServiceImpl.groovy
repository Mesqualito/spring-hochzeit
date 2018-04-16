package rocks.gebsattel.hochzeit.services.mapservices

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import rocks.gebsattel.hochzeit.domain.Customer
import rocks.gebsattel.hochzeit.domain.DomainObject
import rocks.gebsattel.hochzeit.services.CustomerService

@Service
@Profile("map")
class CustomerServiceImpl extends AbstractMapService implements CustomerService {

    @Override
    List<DomainObject> listAll(){ super.listAll() }

    @Override
    Customer getById(Integer id){ (Customer) super.getById(id) }

    @Override
    Customer saveOrUpdate(Customer domainObject) { (Customer) super.saveOrUpdate(domainObject) }

    @Override
    void delete(Integer id){ super.delete(id) }
}
