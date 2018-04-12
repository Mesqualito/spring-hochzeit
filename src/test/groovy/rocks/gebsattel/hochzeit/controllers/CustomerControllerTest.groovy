package rocks.gebsattel.hochzeit.controllers

import rocks.gebsattel.hochzeit.domain.Customer
import rocks.gebsattel.hochzeit.services.CustomerService
import org.junit.Before
import org.junit.Test
import org.mockito.*
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders

import static org.hamcrest.Matchers.*
import static org.mockito.Mockito.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

class CustomerControllerTest {

    @Mock
    private CustomerService customerService

    @InjectMocks
    private CustomerController customerController

    private MockMvc mockMvc

    @Before
    // gives CustomerController configured with a Mockito Mock for customerService
    void setup() {
        MockitoAnnotations.initMocks(this)

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build()
    }

    @Test
    void testList() throws Exception {
        List<Customer> customers = new ArrayList<>()
        customers.add(new Customer())
        customers.add(new Customer())

        when(customerService.listAll()).thenReturn((List) customers)

        mockMvc.perform(get("/customer/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/list"))
                .andExpect(model().attribute("customers", hasSize(2)))
    }

    @Test
    void testShow() throws Exception {
        Integer id = 1

        when(customerService.getById(id)).thenReturn(new Customer())

        mockMvc.perform(get("/customer/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/show"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)))
    }

    @Test
    void testEdit() throws Exception {
        Integer id = 1

        when(customerService.getById(id)).thenReturn(new Customer())

        mockMvc.perform(get("/customer/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customerform"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)))
    }

    @Test
    void testNew() throws Exception{
        verifyZeroInteractions(customerService)

        mockMvc.perform(get("/customer/new"))
        .andExpect(status().isOk())
        .andExpect(view().name("customer/customerform"))
        .andExpect(model().attribute("customer", instanceOf(Customer.class)))
    }

    @Test
    void testSaveOrUpdate() throws Exception{
        Integer id = 1

        String firstName = "Testos"
        String lastName = "Terrone"
        String addressLine1 = "Musterstraße 2"
        String addressLine2 = "Hinterhaus"
        String city = "Nordhamburgton"
        String state = "Ost-West-Nordfalen"
        String zipCode = "912837"
        String eMail = "testos@terrone.com"
        String phoneNr = "0175-16273641435"

        Customer returnCustomer = new Customer()
        returnCustomer.setId(id)
        returnCustomer.setFirstName(firstName)
        returnCustomer.setLastName(lastName)
        returnCustomer.setAddressLine1(addressLine1)
        returnCustomer.setAddressLine2(addressLine2)
        returnCustomer.setCity(city)
        returnCustomer.setState(state)
        returnCustomer.setZipCode(zipCode)
        returnCustomer.seteMail(eMail)
        returnCustomer.setPhoneNr(phoneNr)

        when(customerService.saveOrUpdate(Matchers.<Customer>any())).thenReturn(returnCustomer)

        mockMvc.perform(post("/customer")
        .param("id", "1")
        .param("firstName", firstName)
        .param("lastName", lastName)
        .param("addressLine1", addressLine1)
        .param("addressLine2", addressLine2)
        .param("city", city)
        .param("state", state)
        .param("zipCode", zipCode)
        .param("eMail", eMail)
        .param("phoneNr", phoneNr))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/customer/show/1"))
        .andExpect(model().attribute("customer", instanceOf(Customer.class)))
        .andExpect(model().attribute("customer", hasProperty("firstName", is(firstName))))
                .andExpect(model().attribute("customer", hasProperty("lastName", is(lastName))))
                .andExpect(model().attribute("customer", hasProperty("addressLine1", is(addressLine1))))
                .andExpect(model().attribute("customer", hasProperty("addressLine2", is(addressLine2))))
                .andExpect(model().attribute("customer", hasProperty("city", is(city))))
                .andExpect(model().attribute("customer", hasProperty("state", is(state))))
                .andExpect(model().attribute("customer", hasProperty("zipCode", is(zipCode))))
                .andExpect(model().attribute("customer", hasProperty("eMail", is(eMail))))
                .andExpect(model().attribute("customer", hasProperty("phoneNr", is(phoneNr))))

        ArgumentCaptor<Customer> customerCaptor = ArgumentCaptor.forClass(Customer.class)
        verify(customerService).saveOrUpdate(customerCaptor.capture())

        Customer boundCustomer = customerCaptor.getValue()

        assert id == boundCustomer.id
        assert firstName == boundCustomer.firstName
        assert lastName == boundCustomer.lastName
        assert addressLine1 == boundCustomer.addressLine1
        assert addressLine2 == boundCustomer.addressLine2
        assert city == boundCustomer.city
        assert state == boundCustomer.state
        assert zipCode == boundCustomer.zipCode
        assert eMail == boundCustomer.eMail
        assert phoneNr == boundCustomer.phoneNr
    }
}
