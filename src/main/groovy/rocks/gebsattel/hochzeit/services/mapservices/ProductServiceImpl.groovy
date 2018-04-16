package rocks.gebsattel.hochzeit.services.mapservices

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import rocks.gebsattel.hochzeit.domain.DomainObject
import rocks.gebsattel.hochzeit.domain.Product
import rocks.gebsattel.hochzeit.services.ProductService

@Service
@Profile("map")
class ProductServiceImpl extends AbstractMapService implements ProductService {

    @Override
    List<DomainObject> listAll() { super.listAll() }

    @Override
    Product getById(Integer id) { (Product) super.getById(id) }

    @Override
    Product saveOrUpdate(Product domainObject) { (Product) super.saveOrUpdate(domainObject) }

    @Override
    void delete(Integer id) { super.delete(id) }
}
