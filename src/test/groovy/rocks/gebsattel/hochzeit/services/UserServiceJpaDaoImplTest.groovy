package rocks.gebsattel.hochzeit.services

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import rocks.gebsattel.hochzeit.domain.User

@RunWith(SpringJUnit4ClassRunner)
@SpringBootTest("JpaIntegrationConfig")
@ActiveProfiles("jpadao")
class UserServiceJpaDaoImplTest {

    private UserService userService

    @Autowired
    void setUserService(UserService userService) {
        this.userService = userService
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

}
