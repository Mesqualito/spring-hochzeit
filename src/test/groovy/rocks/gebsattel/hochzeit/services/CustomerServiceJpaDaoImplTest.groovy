package rocks.gebsattel.hochzeit.services

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import rocks.gebsattel.hochzeit.domain.Customer

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

}
