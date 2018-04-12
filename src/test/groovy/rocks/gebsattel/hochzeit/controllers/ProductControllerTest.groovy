package rocks.gebsattel.hochzeit.controllers

import org.junit.Before
import org.junit.Test
import org.mockito.*
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import rocks.gebsattel.hochzeit.domain.Product
import rocks.gebsattel.hochzeit.services.ProductService

import static org.hamcrest.Matchers.*
import static org.mockito.Mockito.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

class ProductControllerTest {

    @Mock
    // declare as Mockito Mock object
    private ProductService productService

    @InjectMocks
    // sets up controller and injects mock objects into it
    private ProductController productController

    private MockMvc mockMvc

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this) // initialises controller and mocks
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build()
    }

    @Test
    void testList() throws Exception {

        List<Product> products = new ArrayList<>()
        products.add(new Product())
        products.add(new Product())

        // specific Mockito interaction, tell stub to return list of products
        when(productService.listAll()).thenReturn((List) products) // stripping the generics for Mockito

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/list"))
                .andExpect(model().attribute("products", hasSize(2)))
    }

    @Test
    void testShow() throws Exception {
        Integer id = 1

        // tell Mockito stub to return new product for ID 1
        when(productService.getById(id)).thenReturn(new Product())

        mockMvc.perform(get("/product/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/show"))
                .andExpect(model().attribute("product", instanceOf(Product.class)))
    }

    @Test
    void testEdit() throws Exception {
        Integer id = 1

        // tell Mockito stub to return new product for ID 1
        when(productService.getById(id)).thenReturn(new Product())

        mockMvc.perform(get("/product/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/productform"))
                .andExpect(model().attribute("product", instanceOf(Product.class)))
    }

    @Test
    void testNew() throws Exception {
        Integer id = 1

        // testing: should not call service
        verifyZeroInteractions(productService)

        mockMvc.perform(get("/product/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/productform"))
                .andExpect(model().attribute("product", instanceOf(Product.class)))
    }

    @Test
    void testSaveOrUpdate() throws Exception {
        Integer id = 1
        String description = "Mockito Test Description"
        BigDecimal price = new BigDecimal("413.80")
        String imageUrl = "http://hochzeit.gebsattel.rocks/nowhere/products/product1.jpg"

        Product returnProduct = new Product()
        returnProduct.setId(id)
        returnProduct.setDescription(description)
        returnProduct.setPrice(price)
        returnProduct.setImageUrl(imageUrl)

        when(productService.saveOrUpdate(Matchers.<Product> any())).thenReturn(returnProduct)

        mockMvc.perform(post("/product")
                .param("id", "1")
                .param("description", description)
                .param("price", "413.80")
                .param("imageUrl", "http://hochzeit.gebsattel.rocks/nowhere/products/product1.jpg"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/product/show/1"))
                .andExpect(model().attribute("product", instanceOf(Product.class)))
                .andExpect(model().attribute("product", hasProperty("id", is(id))))
                .andExpect(model().attribute("product", hasProperty("description", is(description))))
                .andExpect(model().attribute("product", hasProperty("price", is(price))))
                .andExpect(model().attribute("product", hasProperty("imageUrl", is(imageUrl))))

        //verify properties of bound object
        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class)
        verify(productService).saveOrUpdate(productCaptor.capture());

        Product boundProduct = productCaptor.getValue()

        assert id == boundProduct.id
        assert description == boundProduct.description
        assert price == boundProduct.price
        assert imageUrl == boundProduct.imageUrl
    }

    @Test
    void testDelete() throws Exception {
        Integer id = 1

        mockMvc.perform(get("/product/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/product/list"))

        verify(productService, times(1)).delete(id)
    }

}

