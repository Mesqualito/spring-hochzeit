package rocks.gebsattel.hochzeit.services

import org.springframework.stereotype.Service
import rocks.gebsattel.hochzeit.domain.Customer
import rocks.gebsattel.hochzeit.domain.DomainObject

@Service
class CustomerServiceImpl extends AbstractMapService implements CustomerService {

    @Override
    List<DomainObject> listAll(){ super.listAll() }

    @Override
    Customer getById(Integer id){ (Customer) super.getById(id) }

    @Override
    Customer saveOrUpdate(Customer domainObject) { (Customer) super.saveOrUpdate(domainObject) }

    @Override
    void delete(Integer id){ super.delete(id) }

    @Override
    protected void loadDomainObjects(){
        domainMap = new HashMap<>()

        Customer customer1 = new Customer()
        customer1.setId(1)
        customer1.setFirstName("Hannes")
        customer1.setLastName("Burgermeister")
        customer1.setAddressLine1("Teststr. 1")
        customer1.setCity("München")
        customer1.setState("Bayern")
        customer1.setZipCode("28271")
        customer1.seteMail("wunder@pastillen.com")
        customer1.setPhoneNr("089/433284323-21")

        domainMap.put(1, customer1)

        Customer customer2 = new Customer()
        customer2.setId(2)
        customer2.setFirstName("Robert")
        customer2.setLastName("Müller")
        customer2.setAddressLine1("Am Ampelchen 4")
        customer2.setAddressLine2("Untergeschoß")
        customer2.setCity("Habsburg")
        customer2.setState("Ruhnewald")
        customer2.setZipCode("98111")
        customer2.seteMail("r.mueller@gmx.me")
        customer2.setPhoneNr("09383/123-456 78")

        domainMap.put(2, customer2)

        Customer customer3 = new Customer()
        customer3.setId(3)
        customer3.setFirstName("Aldi")
        customer3.setLastName("Wachau")
        customer3.setAddressLine1("Landstr. 27")
        customer3.setAddressLine2("Kellereingang")
        customer3.setCity("Nürnberg")
        customer3.setState("Unterfranken")
        customer3.setZipCode("90931")
        customer3.seteMail("aldi@wachau.rocks")
        customer3.setPhoneNr("0911/983461")

        domainMap.put(3, customer3)

        assert domainMap.size() == 3
        println "\'domainMap.size()' (Customer) = ${domainMap.size()}"
    }
}
