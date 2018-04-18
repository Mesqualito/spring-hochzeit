package rocks.gebsattel.hochzeit.services

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import rocks.gebsattel.hochzeit.domain.Cart
import rocks.gebsattel.hochzeit.domain.CartDetail
import rocks.gebsattel.hochzeit.domain.User
import rocks.gebsattel.hochzeit.domain.Product
import rocks.gebsattel.hochzeit.domain.Customer
import rocks.gebsattel.hochzeit.services.UserService
import rocks.gebsattel.hochzeit.services.ProductService

@RunWith(SpringJUnit4ClassRunner)
@SpringBootTest("JpaIntegrationConfig")
@ActiveProfiles("jpadao")
class UserServiceJpaDaoImplTest {

    UserService userService
    ProductService productService

    @Autowired
    void setUserService(UserService userService) {
        this.userService = userService
    }

    @Autowired
    void setProductService(ProductService productService) {
        this.productService = productService
    }

    @Test
    void testSaveOfUser() throws Exception {
        User user = new User()

        user.setUsername("testuser")
        user.setPassword("testpassword")

        User savedUser = userService.saveOrUpdate(user)

        assert savedUser.id != null
        assert savedUser.getEncryptedPassword() != null

        println "Encrypted Password"
        println savedUser.getEncryptedPassword()

    }

    @Test
    void testSaveOfUserWithCustomer() throws Exception {

        User user = new User()

        user.setUsername("I'm a username")
        user.setPassword("myPassword")

        Customer customer = new Customer()
        customer.setFirstName("Customer FirstName")
        customer.setLastName("Customer LastName")

        user.setCustomer(customer)

        User savedUser = userService.saveOrUpdate(user)

        assert savedUser.getId() != null
        assert savedUser.getVersion() != null
        assert savedUser.getCustomer() != null
        assert savedUser.getCustomer().getId() != null
    }

    @Test
    void testAddCartToUser() throws Exception {
        User user = new User()

        user.setUsername("UserWithCart")
        user.setPassword("cartuser")

        user.setCart(new Cart())

        User savedUser = userService.saveOrUpdate(user)

        assert savedUser.getId() != null
        assert savedUser.getVersion() != null
        assert savedUser.getCart() != null
        assert savedUser.getCart().getId() != null

    }

    @Test
    void testAddCartToUserWithCartDetails() throws Exception {
        User user = new User()

        user.setUsername("UserWithCart")
        user.setPassword("cartuser")

        user.setCart(new Cart())

        List<Product> storedProducts = (List<Product>) productService.listAll()

        CartDetail cartItemOne = new CartDetail()
        cartItemOne.setProduct(storedProducts.get(0))
        user.getCart().addCartDetail(cartItemOne)

        CartDetail cartItemTwo = new CartDetail()
        cartItemTwo.setProduct(storedProducts.get(1))
        user.getCart().addCartDetail(cartItemTwo)

        User savedUser = userService.saveOrUpdate(user)

        assert savedUser.getId() != null
        assert savedUser.getVersion() != null
        assert savedUser.getCart() != null
        assert savedUser.getCart().getId() != null
        assert savedUser.getCart().getCartDetails().size() == 2
    }

    @Test
    void testAddAndRemoveCartToUserWithCartDetails() throws Exception {
        User user = new User()

        user.setUsername("UserWithCart")
        user.setPassword("cartuser")

        user.setCart(new Cart())

        List<Product> storedProducts = (List<Product>) productService.listAll()

        CartDetail cartItemOne = new CartDetail()
        cartItemOne.setProduct(storedProducts.get(0))
        user.getCart().addCartDetail(cartItemOne)

        CartDetail cartItemTwo = new CartDetail()
        cartItemTwo.setProduct(storedProducts.get(1))
        user.getCart().addCartDetail(cartItemTwo)

        User savedUser = userService.saveOrUpdate(user)

        assert savedUser.getCart().getCartDetails().size() == 2

        savedUser.getCart().removeCartDetail(savedUser.getCart().getCartDetails().get(0))

        userService.saveOrUpdate(savedUser)

        assert savedUser.getCart().getCartDetails().size() == 1
    }

}
