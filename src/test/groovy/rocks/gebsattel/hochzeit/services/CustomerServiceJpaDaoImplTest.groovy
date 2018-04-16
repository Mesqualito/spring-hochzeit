package rocks.gebsattel.hochzeit.services

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import rocks.gebsattel.hochzeit.domain.Customer
import rocks.gebsattel.hochzeit.domain.User

@RunWith(SpringJUnit4ClassRunner)
@SpringBootTest("JpaIntegrationConfig")
@ActiveProfiles("jpadao")
class CustomerServiceJpaDaoImplTest {

    private CustomerService customerService

    @Autowired
    void setCustomerService(CustomerService customerService){
        this.customerService = customerService
    }

    @Test
    void testList() throws Exception{
        List<Customer> customers = (List<Customer>) customerService.listAll()
        assert customers.size() == 3
    }

    @Test
    void testSaveWithUser(){
        Customer customer = new Customer()
        User user = new User()
        user.setUsername("This is a username")
        user.setPassword("MyTestPassword")
        customer.setUser(user)
        Customer savedCustomer = customerService.saveOrUpdate(customer)
        assert savedCustomer.getUser().getId() != null
    }

}
